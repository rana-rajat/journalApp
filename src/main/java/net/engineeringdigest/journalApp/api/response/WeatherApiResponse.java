package net.engineeringdigest.journalApp.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherApiResponse {
    private String message;
    private WeatherResponse weatherResponse;
}
