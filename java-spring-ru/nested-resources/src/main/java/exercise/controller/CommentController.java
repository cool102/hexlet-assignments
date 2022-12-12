package exercise.controller;

import exercise.ResourceNotFoundException;
import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Comment comment = commentRepository.findByIdAndPost(commentId, post);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found");
        }
        commentRepository.delete(comment);
    }

    @PostMapping("/{postId}/comments")
    public void createComment(@PathVariable long postId, @RequestBody Comment newComment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        newComment.setPost(post);
        post.getComments().add(newComment);
        postRepository.save(post);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public void editCommentByPost(@PathVariable long postId, @PathVariable long commentId, @RequestBody Comment newComment) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        Comment found_comment = post.getComments()
                .stream()
                .filter(comment -> comment.getId() == commentId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        long id = found_comment.getId();
        newComment.setId(id);
        newComment.setPost(post);
        commentRepository.save(newComment);

    }

    @GetMapping("/{id}/comments")
    public Iterable<Comment> getAllCommentsByPostId(@PathVariable long id) {
        return commentRepository.findAllByPostId(id);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getCommentByPostIdAndCommentId(
            @PathVariable Post postId,
            @PathVariable long commentId
    ) {
        Comment comment = commentRepository.findByIdAndPost(commentId, postId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found");
        } else {
            return commentRepository.findByIdAndPost(commentId, postId);
        }

    }
    // END
}
