package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public void save(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntryList().add(saved); // yhe in memory ho ga
            userService.saveEntry(user);
        } catch (Exception e) {
            logger.info("Exception");
            throw new RuntimeException(e);
        }

    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + userName);
        }
        boolean removed = user.getJournalEntryList().removeIf(entry -> entry.getId().equals(id));

        if (removed) {
            userService.saveEntry(user);
            journalEntryRepo.deleteById(id);
            return true;
        } else {
            return false; // Nothing was deleted
        }
    }

    @Transactional
    public Optional<JournalEntry> updateById(ObjectId id, JournalEntry newEntry, String userName) {
        User user = userService.findByUserName(userName);
        if (user == null || user.getJournalEntryList().stream().noneMatch(e -> e.getId().equals(id))) {
            return Optional.empty();
        }

        return journalEntryRepo.findById(id).map(entry -> {
            if (!newEntry.getTitle().isBlank()) {
                entry.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isBlank()) {
                entry.setContent(newEntry.getContent());
            }
            return journalEntryRepo.save(entry);
        });
    }
}

