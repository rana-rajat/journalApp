package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

public class WeatherService {
    public static final String apiKey = "98b53a297713cb93c00261cac97036c7";

    public static final String API = "http://api.weatherstack.com/current?access_key=API&query=CITY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherService getWeather(String city){
        String finalApi = API.replace("API",apiKey).replace("CITY",city);
//       forPost
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("key","Value");
//        String requestBody = "{\n" +
//                "  \"userName\":\"Rana\",\n" +
//                "  \"password\":\"Rana\"\n" +
//                "}";
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody,httpHeaders);
//        HttpEntity<WeatherService> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherService.class);
        //Also
//        UserDetails user = User.builder().username("Rana").password("Rana").build();
//        HttpEntity<net.engineeringdigest.journalApp.entity.User> httpEntiity = new HttpEntity<>(user);
//        HttpEntity<WeatherService> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherService.class);

        HttpEntity<WeatherService> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherService.class);
        return response.getBody();
    }

}
