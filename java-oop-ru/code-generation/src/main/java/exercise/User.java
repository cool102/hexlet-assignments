package exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@AllArgsConstructor
@Data
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}