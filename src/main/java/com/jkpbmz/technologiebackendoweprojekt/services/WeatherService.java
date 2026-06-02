package com.jkpbmz.technologiebackendoweprojekt.services;

import com.jkpbmz.technologiebackendoweprojekt.projections.weather.CurrentWeatherDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class WeatherService {
    private RestClient weatherClient;

    public CurrentWeatherDTO fetchCurrentWeather(double latitude, double longitude) {
        return weatherClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .queryParam("units", "metric")
                        .queryParam("lang", "pl")
                        .queryParam("exclude", "minutely,hourly,daily,alerts")
                        .build())
                .retrieve()
                .body(CurrentWeatherDTO.class);
    }
}
