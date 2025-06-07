package net.engineeringdigest.journalApp.Scheduler;

import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    //@Scheduled(cron = "0 9 * * 0")//for evey sunday at 9 am
    @Scheduled(cron = "0 * * ? * *")//for every one minute
    public void fetchUserAndSendSAMail() {
        List<User> users = userRepository.listUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntryList = user.getJournalEntryList();
            //here we first get the entries based on the data And then we get only the content
            List<String> filterEntries = journalEntryList.stream()
                    .filter(entry -> entry.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getContent)
                    .toList();
            String entry = String.join("", filterEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment For last 7 days", sentiment);
        }

    }

    //writing scheduler for every 30 minute
    @Scheduled(cron = "*/30 * * * *")
    public void clearAppCache() {
        appCache.init();
    }

}

