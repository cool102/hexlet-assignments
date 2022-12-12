package exercise.repository;

import exercise.model.Comment;
import exercise.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    Iterable<Comment> findAllByPostId(long id);
    Comment findByIdAndPost(long commentId, Post postId);
    // END
}
