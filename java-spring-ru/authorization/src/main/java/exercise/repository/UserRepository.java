package exercise.repository;

import exercise.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // BEGIN
    User findByEmail(String email);
    // END
}
