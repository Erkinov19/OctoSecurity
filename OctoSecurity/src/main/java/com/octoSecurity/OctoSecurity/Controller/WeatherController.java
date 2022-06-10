package com.octoSecurity.OctoSecurity.Controller;

import Service.WeatherService;
import com.octoSecurity.OctoSecurity.dto.ApiWeatherDto;
import com.octoSecurity.OctoSecurity.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping()
    public ResponseEntity<?> getWeather (@RequestBody ApiWeatherDto dto){
        WeatherDto result = weatherService.getWeather(dto.getLon(),dto.getLat());
        return ResponseEntity.ok(result);
    }
}
