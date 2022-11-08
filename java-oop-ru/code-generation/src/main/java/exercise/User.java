package exercise;

import lombok.Value;

@AllArgsConstructor
@Getter
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}