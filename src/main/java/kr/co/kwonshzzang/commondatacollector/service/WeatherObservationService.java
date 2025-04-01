package kr.co.kwonshzzang.commondatacollector.service;

import kr.co.kwonshzzang.commondatacollector.dto.weather.WeatherObservation;
import kr.co.kwonshzzang.commondatacollector.repository.KafkaRepository;
import kr.co.kwonshzzang.model.avro.WeatherObserveValue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherObservationService {
    private final KafkaRepository kafkaRepository;

    @Value("${spring.kafka.topic-1}")
    private String weatherObservationTopic;

    public void doService(WeatherObservation weatherObservation) {
        String resultCode = weatherObservation.getResponse().getHeader().getResultCode();
        if(!resultCode.equals("00")) return;

        List<WeatherObserveValue> observationValues
                = weatherObservation.getResponse().getBody().getItems().getItem()
                  .stream().map(WeatherObservation.Item::toWeatherObserveValue).toList();

        kafkaRepository.sendWeatherObservation(weatherObservationTopic, observationValues);
    }
}
