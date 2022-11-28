package exercise;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public String root() {
        return "Welcome to Spring";
    }
    @PostMapping("/people")
    public void createUser(@RequestBody Person person) {
        if (person == null) {
            personRepository.save(person);
        }
    }
    @DeleteMapping("/people/{id}")
    public void deleteUser(@PathVariable ("id") int id) {
        Person finded = personRepository.findById(id);
        personRepository.delete(finded);
    }
    @PatchMapping("/people/{id}")
    public void updateUser(@PathVariable ("id") int id){
        Person update = personRepository.findById(id);
        personRepository.save(update);
    }
}
