package kr.co.kwonshzzang.commondatacollector.repository;

import kr.co.kwonshzzang.commondatacollector.dto.SensorDataDto;
import kr.co.kwonshzzang.commondatacollector.dto.weather.WeatherPrediction;
import kr.co.kwonshzzang.commondatacollector.service.WeatherPredictionService;
import kr.co.kwonshzzang.model.avro.SensorData;
import kr.co.kwonshzzang.model.avro.WeatherObserveValue;
import kr.co.kwonshzzang.model.avro.WeatherPredictValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class KafkaRepository {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendWeatherObservation(String topic, List<WeatherObserveValue> observationValues) {
        for (WeatherObserveValue observeValue : observationValues) {
          send(topic, observeValue);
        }
    }

    public void sendWeatherPrediction(String topic, List<WeatherPredictValue> predictValues) {
        for (WeatherPredictValue predictValue : predictValues) {
            send(topic, predictValue);
        }
    }

    public void sendSensorData(String topic, SensorData sensorData) {
        String key = sensorData.getSensorId() + "|" + sensorData.getTime() + "|" +
                sensorData.getDataType()  + "|" + sensorData.getStatus();

        kafkaTemplate.send(topic, key,sensorData);
        writeLog(topic, key, sensorData);
    }

    public void send(String topic, WeatherObserveValue observeValue) {
        String key = observeValue.getBaseDate() + "|" + observeValue.getBaseTime() + "|" +
                observeValue.getCategory()  + "|" + observeValue.getNx() + "|" + observeValue.getNy();

        kafkaTemplate.send(topic, key, observeValue);
        writeLog(topic, key, observeValue);
    }

    public void send(String topic, WeatherPredictValue predictValue) {
        String key = predictValue.getBaseDate() + "|" + predictValue.getBaseTime() + "|" +
                predictValue.getCategory()  + "|" + predictValue.getFcstDate() + "|" + predictValue.getFcstTime() + "|" +
                predictValue.getNx() + "|" + predictValue.getNy();

        kafkaTemplate.send(topic, key, predictValue);
        writeLog(topic, key, predictValue);

    }

    private void writeLog(String topic, String key, Object value) {
        log.info("Produced event to topic = {}: key = {} value = {}", topic , key, value);
    }


}
