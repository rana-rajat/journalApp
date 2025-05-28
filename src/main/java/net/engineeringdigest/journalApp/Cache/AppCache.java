package net.engineeringdigest.journalApp.Cache;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppCache {

    Map< String,String > appCache;

    @PostConstruct
    public void init(){
        appCache = null;
    }
}
