package service;

import form.PostForm;
import model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService {
    private List<Post> posts = new ArrayList<>();

    public PostService() {
        posts.add(new Post(1, "PW45S", "Cool subject"));
        posts.add(new Post(2, "Play!", "Cool framework"));
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post getPost(int postId) {
        // TODO: load from database
        Optional<Post> requestedPost = posts.stream()
                .filter(post -> post.getId() == postId)
                .findAny();
        return requestedPost.orElse(null);
    }

    public void add(PostForm postForm) {
        // TODO: save into the database
        posts.add(new Post(3, postForm.getTitle(), postForm.getContent()));
    }

    public void delete(int postId) {
        posts.removeIf(post -> post.getId() == postId);
    }
}
