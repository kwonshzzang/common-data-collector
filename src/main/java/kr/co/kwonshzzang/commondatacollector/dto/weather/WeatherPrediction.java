package kr.co.kwonshzzang.commondatacollector.dto.weather;

import kr.co.kwonshzzang.commondatacollector.util.NumberFormatUtil;
import kr.co.kwonshzzang.model.avro.WeatherPredictValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherPrediction {
    private Response response;

    @Getter
    @Setter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Body {
        private String dataType;
        private Items items;
        private Integer pageNo;
        private Integer numOfRows;
        private Integer totalCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Items {
        private List<Item> item;
    }

    @Getter
    @Setter
    public static class Item {
        private String baseDate;
        private String baseTime;
        private String category;
        private String fcstDate;
        private String fcstTime;
        private String fcstValue;
        private int nx;
        private int ny;

        public WeatherPredictValue toWeatherPredictValue() {
            Double predictValue = 0.0;
            if(NumberFormatUtil.isNumberic(fcstValue)) {
                predictValue = Double.parseDouble(fcstValue);
            } else {
                predictValue = -999999.0;
            }

            return WeatherPredictValue.newBuilder()
                    .setBaseDate(baseDate)
                    .setBaseTime(baseTime)
                    .setCategory(category)
                    .setFcstDate(fcstDate)
                    .setFcstTime(fcstTime)
                    .setNx(String.valueOf(nx))
                    .setNy(String.valueOf(ny))
                    .setFcstValue(predictValue)
                    .build();
        }

    }
}
