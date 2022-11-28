package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping("")
    public String getPersons() throws JsonProcessingException {
        String sql = "SELECT * FROM person";
        List<Person> persons = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(persons);
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id) throws JsonProcessingException {
        String sql = "SELECT * FROM person";
        List<Person> persons = jdbc.query(sql, BeanPropertyRowMapper.newInstance(Person.class));
        Person finded = persons.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Not users with id: " + id));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(finded);
    }

    // END
}
