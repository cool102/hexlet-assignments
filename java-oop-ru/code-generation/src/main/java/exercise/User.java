package exercise;

import lombok.Value;

@AllArgsConstructor
@Data
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}