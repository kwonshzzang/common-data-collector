package kr.co.kwonshzzang.commondatacollector.dto;

import kr.co.kwonshzzang.model.avro.SensorData;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class SensorDataDto {
    private Instant time;
    private String dataType;
    private String sensorId;
    private String status;
    private Double aValue;
    private String dValue;
    private String tValue;

    public SensorData toSensorData() {
        return SensorData.newBuilder()
                .setTime(time)
                .setDataType(dataType)
                .setSensorId(sensorId)
                .setStatus(status)
                .setValue(aValue)
                .build();
    }
}
