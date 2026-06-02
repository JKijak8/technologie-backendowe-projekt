package com.jkpbmz.technologiebackendoweprojekt.controllers;

import com.jkpbmz.technologiebackendoweprojekt.projections.weather.CurrentWeatherDTO;
import com.jkpbmz.technologiebackendoweprojekt.services.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private WeatherService weatherService;

    @GetMapping("/current")
    public CurrentWeatherDTO getCurrentWeather(@RequestParam("lat") double latitude,
                                               @RequestParam("lon") double longitude) {
        return weatherService.fetchCurrentWeather(latitude, longitude);
    }
}
