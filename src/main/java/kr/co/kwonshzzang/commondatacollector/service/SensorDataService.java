package kr.co.kwonshzzang.commondatacollector.service;

import kr.co.kwonshzzang.commondatacollector.dto.SensorDataDto;
import kr.co.kwonshzzang.commondatacollector.repository.KafkaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SensorDataService {
    private final KafkaRepository kafkaRepository;

    @Value("${spring.kafka.topic-3}")
    private String sensorDataTopic;

    public void doService(SensorDataDto sensorDataDto) {
        kafkaRepository.sendSensorData(sensorDataTopic, sensorDataDto.toSensorData());
    }
}
