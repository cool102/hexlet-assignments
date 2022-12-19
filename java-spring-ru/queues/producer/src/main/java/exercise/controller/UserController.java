package exercise.controller;

import exercise.MessageSender;
import exercise.UserNotFoundException;
import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSender messageSender;

    @GetMapping(path = "")
    public Iterable<User> getPosts() {
        return userRepository.findAll();
    }

    @PostMapping(path = "")
    public User createUser(@RequestBody User user) {

        User savedUser = userRepository.save(user);

        String message = "User " + user.getName() + " has been registered";
        // BEGIN
        messageSender.sendMessage(message);
        // END
        return savedUser;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable long id) {

        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String message = "User " + userToDelete.getName() + " has been deleted";
        userRepository.delete(userToDelete);
        // BEGIN
        messageSender.sendMessage(message);
        // END

    }
}

