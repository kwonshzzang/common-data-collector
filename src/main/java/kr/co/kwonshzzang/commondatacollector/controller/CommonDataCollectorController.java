package kr.co.kwonshzzang.commondatacollector.controller;

import kr.co.kwonshzzang.commondatacollector.dto.SensorDataDto;
import kr.co.kwonshzzang.commondatacollector.dto.weather.WeatherObservation;
import kr.co.kwonshzzang.commondatacollector.dto.weather.WeatherPrediction;
import kr.co.kwonshzzang.commondatacollector.service.SensorDataService;
import kr.co.kwonshzzang.commondatacollector.service.WeatherObservationService;
import kr.co.kwonshzzang.commondatacollector.service.WeatherPredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommonDataCollectorController {
    private final WeatherObservationService weatherObservationService;
    private final WeatherPredictionService weatherPredictionService;
    private final SensorDataService sensorDataService;

    @GetMapping
    public String data() {
        return "collect data home";
    }

    @PostMapping("/weather/observation")
    public void addWeatherObserveValues(@RequestBody WeatherObservation weatherObservation) {
        weatherObservationService.doService(weatherObservation);
    }

    @PostMapping("/weather/prediction")
    public void addWeatherPredictValues(@RequestBody WeatherPrediction weatherPrediction) {
       weatherPredictionService.doService(weatherPrediction);
    }

    @PostMapping("/sensordata")
    public void addSensorData(@RequestBody SensorDataDto sensorDataDto) {
        sensorDataService.doService(sensorDataDto);
    }


}
