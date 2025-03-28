package kr.co.kwonshzzang.commondatacollector.service;

import kr.co.kwonshzzang.commondatacollector.dto.weather.WeatherObservation;
import kr.co.kwonshzzang.commondatacollector.dto.weather.WeatherPrediction;
import kr.co.kwonshzzang.commondatacollector.repository.KafkaRepository;
import kr.co.kwonshzzang.model.avro.WeatherObserveValue;
import kr.co.kwonshzzang.model.avro.WeatherPredictValue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherPredictionService {
    private final KafkaRepository kafkaRepository;

    @Value("${spring.kafka.weather-prediction-topic}")
    private String weatherPredictionTopic;

    public void doService(WeatherPrediction weatherPrediction) {
        String resultCode = weatherPrediction.getResponse().getHeader().getResultCode();
        if (!resultCode.equals("00")) return;

        List<WeatherPredictValue> predictValues
                = weatherPrediction.getResponse().getBody().getItems().getItem()
                .stream().map(WeatherPrediction.Item::toWeatherPredictValue).toList();

        kafkaRepository.sendWeatherPrediction(weatherPredictionTopic, predictValues);
    }
}
