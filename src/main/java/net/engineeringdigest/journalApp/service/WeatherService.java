package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    public String apiKey;

    @Autowired
    private AppCache appCacheObj;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather of " + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            String finalApi = appCacheObj.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.API_KEY, apiKey).replace(Placeholders.CITY, city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("Weather of "+city,body, 300L);
            }
            return response.getBody();
        }

//       forPost
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("key","Value");
//        String requestBody = "{\n" +
//                "  \"userName\":\"Rana\",\n" +
//                "  \"password\":\"Rana\"\n" +
//                "}";
//        ResponseEntity<String> httpEntity = new HttpEntity<>(requestBody,httpHeaders);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherService.class);
        //Also
//        UserDetails user = User.builder().username("Rana").password("Rana").build();
//        ResponseEntity<net.engineeringdigest.journalApp.entity.User> httpEntiity = new HttpEntity<>(user);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherService.class);


    }

}
