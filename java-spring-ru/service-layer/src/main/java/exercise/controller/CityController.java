package exercise.controller;

import exercise.model.CityDto;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping("/cities/{id}")
    public CityDto getWeatherInCity(@PathVariable("id") long id) {
        return weatherService.getWeatherByCityId(id);
    }

    @GetMapping("/search")
    public List<Map<String, String>> getWeatherInCitiesStartsWith(@RequestParam(required = false) String name) {
        if (name == null) {
            return weatherService.getWeathersForAllCities();
        } else {
            return weatherService.getWeathersForCitiesStartsWith(name);
        }

    }
    // END
}

