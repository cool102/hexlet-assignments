package exercise.model;

import lombok.Data;

@Data
public class CityDto {
    private String name;
    private String temperature;
    private String cloudy;
    private String humidity;
    private String wind;

    public CityDto(String name, String temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public CityDto() {
    }

    public CityDto(String name, String temperature, String cloudy, String humidity, String wind) {
        this.name = name;
        this.temperature = temperature;
        this.cloudy = cloudy;
        this.humidity = humidity;
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCloudy() {
        return cloudy;
    }

    public void setCloudy(String cloudy) {
        this.cloudy = cloudy;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
