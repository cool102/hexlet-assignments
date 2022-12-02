package exercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    private String body;

    private PostState state = PostState.CREATED;

    // BEGIN
    public boolean publish() {
        if (state == PostState.CREATED) {
            state = PostState.PUBLISHED;
            return true;
        } else {
            return false;
        }

    }

    public boolean archive() {
        if (state == PostState.PUBLISHED | state == PostState.CREATED) {
            state = PostState.ARCHIVED;
            return true;
        } else {
            return false;
        }
    }
    // END
}
