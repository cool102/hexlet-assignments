package exercise.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.HttpClient;
import exercise.model.City;
import exercise.model.CityDto;
import exercise.repository.CityRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WeatherService {

    final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
    @Autowired
    CityRepository cityRepository;
    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    @SneakyThrows
    public CityDto getWeatherByCityId(long id) {
        City found = cityRepository.findById(id).get();
        String responce = client.get("http://weather/api/v2/cities/" + found.getName());
        CityDto dto = mapper.readValue(responce, CityDto.class);
        return dto;
    }

    public List<Map<String, String>> getWeathersForAllCities() {
        List<City> cities = cityRepository.findAllByOrderByName();
        return getFinishFormat(cities);

    }

    public List<Map<String, String>> getWeathersForCitiesStartsWith(String prefix) {
        List<City> citiesStartsWithPrefix = cityRepository.findByNameStartingWithIgnoreCase(prefix);
        return getFinishFormat(citiesStartsWithPrefix);
    }

    private List<Map<String, String>> getFinishFormat(List<City> cities) {
        List<Map<String, String>> result = new ArrayList<>();
        for (City cur : cities) {
            Map<String, String> tempMap = new HashMap<>();
            long id = cur.getId();
            CityDto dto = getWeatherByCityId(id);
            String name = dto.getName();
            String temperature = dto.getTemperature();
            tempMap.put("name", name);
            tempMap.put("temperature", temperature);
            result.add(tempMap);
        }
        return result;
    }


    // END
}
