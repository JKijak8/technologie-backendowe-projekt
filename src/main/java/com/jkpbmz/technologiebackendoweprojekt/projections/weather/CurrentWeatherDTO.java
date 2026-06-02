package com.jkpbmz.technologiebackendoweprojekt.projections.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherDTO {
    private WeatherData current;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class WeatherData {
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;

        @JsonProperty("wind_speed")
        private double windSpeed;

        private List<Weather> weather;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Weather {
        private String main;
        private String description;
        private String icon;
    }
}
