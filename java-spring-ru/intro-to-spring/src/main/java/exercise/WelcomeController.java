package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String welcomeName(@RequestParam(required = false) String name) {
        if (name != null) {
            return "Hello, " + name;
        } else  {
            return "Hello, World!";

        }


    }

}
// END
