package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Request request;
    private Location location;
    private Current current;


    @Getter
    @Setter
    public static class Request {
        private String type;
        private String query;
        private String language;
        private String unit;


    }

    @Getter
    @Setter
    public static class Location {
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;

        @JsonProperty("timezone_id")
        private String timezoneId;

        private String localtime;

        @JsonProperty("localtime_epoch")
        private int localtimeEpoch;

        @JsonProperty("utc_offset")
        private String utcOffset;

    }

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;

        @JsonProperty("weather_code")
        private int weatherCode;

        @JsonProperty("weather_icons")
        private List<String> weatherIcons;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        private Astro astro;

        @JsonProperty("air_quality")
        private AirQuality airQuality;

        @JsonProperty("wind_speed")
        private int windSpeed;

        @JsonProperty("wind_degree")
        private int windDegree;

        @JsonProperty("wind_dir")
        private String windDir;

        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;

        @JsonProperty("uv_index")
        private int uvIndex;

        private int visibility;

    }
    @Getter
    @Setter
    public static class Astro {
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;

        @JsonProperty("moon_phase")
        private String moonPhase;

        @JsonProperty("moon_illumination")
        private int moonIllumination;

    }

    @Getter
    @Setter
    public static class AirQuality {
        private String co;
        private String no2;
        private String o3;
        private String so2;

        @JsonProperty("pm2_5")
        private String pm25;

        @JsonProperty("pm10")
        private String pm10;

        @JsonProperty("us-epa-index")
        private String usEpaIndex;

        @JsonProperty("gb-defra-index")
        private String gbDefraIndex;

    }


}
