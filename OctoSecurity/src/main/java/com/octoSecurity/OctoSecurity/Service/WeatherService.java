package com.octoSecurity.OctoSecurity.Service;

import com.octoSecurity.OctoSecurity.dto.WeatherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private String ApiKey  = "96b95107761531f6c017b7a1741714c8";
    private String URL = "https://api.openweathermap.org/data/2.5/weather?";

    public WeatherDto getWeather(Integer lon, Integer lat){
        String connection = String.format("%slat=%s&lon=%s&appid=%s", URL,lat,lon,ApiKey);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity result = restTemplate.getForEntity(connection,WeatherDto.class);
        return (WeatherDto) result.getBody();


    }
}
