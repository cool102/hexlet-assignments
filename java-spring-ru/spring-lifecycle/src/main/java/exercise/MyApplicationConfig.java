package exercise;

import exercise.daytimes.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
// BEGIN
class MyApplicationConfig {
    @Bean
    public Daytime getDayTime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        if (hour >= 6 & hour < 12) {
            return new Morning();
        }
        if (hour >= 12 & hour < 18) {
            return new Day();
        }
        if (hour >= 18 & hour < 23) {
            return new Evening();
        }
        return new Night();
    }
}
// END
