package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final String apiKey = "98b53a297713cb93c00261cac97036c7";

    public static final String API = "http://api.weatherstack.com/current?access_key=API&query=CITY";

    @Autowired
    RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalApi = API.replace("API",apiKey).replace("CITY",city);
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


        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi,HttpMethod.GET,null,WeatherResponse.class);
        return response.getBody();
    }

}
