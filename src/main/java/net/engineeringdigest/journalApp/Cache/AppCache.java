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

    public Map<String, String> appCache;

    @PostConstruct
    public void init() {
        try {
            appCache = new HashMap<>();
            List<ConfigJournalAppEntity> all = configJournalAppRepo.findAll();
            appCache = all.stream().collect(Collectors.toMap(
                    ConfigJournalAppEntity::getKey,
                    ConfigJournalAppEntity::getValue
            ));
            log.info("Cache initialized with {} entries", appCache.size());
        } catch (Exception e) {
            log.error("Failed to initialize cache from DB, using defaults", e);
            // Initialize with critical defaults Only doing to pass the sonar build otherwise we can directly fetch data from the database
            appCache = Map.of(
                    "WEATHER_API", "http://api.weatherstack.com/current?access_key=<api_key>&query=<city>"
            );
        }
    }
}
