package net.engineeringdigest.journalApp.Cache;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AppCache {

    public enum keys {
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepo configJournalAppRepo;


    public Map<String, String> appCache = new HashMap<>();

//    @PostConstruct
//    public void init() {
//        List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll();
//        appCache = all.stream().collect(Collectors.toMap(ConfigJournalAppEntity::getKey,
//                ConfigJournalAppEntity::getValue));
//        log.info("Successfully initialized the Cache ");
//
//    }
    @PostConstruct
    public void init() {
        try {
            List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll();
            appCache = all.stream().collect(Collectors.toMap(
                    ConfigJournalAppEntity::getKey,
                    ConfigJournalAppEntity::getValue
            ));
            log.info("Cache initialized with {} entries", appCache.size());
        } catch (Exception e) {
            log.error("Failed to initialize cache from DB, using defaults", e);
            // Initialize with critical defaults
            appCache = Map.of(
                    "weather_api", "http://api.weatherstack.com/current?access_key=<api_key>&query=<city>"
            );
        }
    }
}
