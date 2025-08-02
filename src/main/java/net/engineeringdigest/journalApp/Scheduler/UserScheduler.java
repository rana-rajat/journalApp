package net.engineeringdigest.journalApp.Scheduler;

import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiment;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

    @Autowired
    KafkaTemplate<String,SentimentData> kafkaTemplate;

    @Scheduled(cron = "0 0 9 ? * SUN")//for evey sunday at 9 am
    //@Scheduled(cron = "0 * * ? * *")//for every one minute
    public void fetchUserAndSendSAMail() {
        List<User> users = userRepository.listUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntryList = user.getJournalEntryList();
            //here we first get the entries based on the data And then we get only the content
            // and later on the code is for which sentiment occur most time
            List<Sentiment> sentiments = journalEntryList.stream()
                    .filter(entry -> entry.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getSentiment)
                    .toList();
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                // with kafka
                SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment For last 7 days"+ mostFrequentSentiment).build();
                kafkaTemplate.send("weekly-sentiments",sentimentData.getEmail(),sentimentData);
                //without kafka
                emailService.sendEmail(user.getEmail(), "Sentiment For last 7 days", mostFrequentSentiment.toString());
            }
        }

    }

    //writing scheduler for every 30 minute
   @Scheduled(cron = "0 0/30 * * * *")
    public void clearAppCache() {
        appCache.init();
    }

}

