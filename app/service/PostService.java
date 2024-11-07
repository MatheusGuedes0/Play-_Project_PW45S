package service;

import form.PostForm;
import model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService {
    private List<Post> posts = new ArrayList<>();
    int id = 0;
    public PostService() {
        posts.add(new Post(id+=1, "PW45S", "Cool subject"));
        posts.add(new Post(id+=1, "Play!", "Cool framework"));
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Post getPost(int postId) {
        
        Optional<Post> requestedPost = posts.stream()
                .filter(post -> post.getId() == postId)
                .findAny();
        return requestedPost.orElse(null);
    }

    public void add(PostForm postForm) {
        
        posts.add(new Post(id+=1, postForm.getTitle(), postForm.getContent()));
    }

    public void delete(int postId) {
        posts.removeIf(post -> post.getId() == postId);
    }

    public boolean updatePost(int postId, PostForm postForm) {
        Optional<Post> postOptional = posts.stream()
                .filter(post -> post.getId() == postId)
                .findFirst();
    
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(postForm.getTitle());
            post.setContent(postForm.getContent());
            return true;
        }
    
        return false;
    }
    
}
