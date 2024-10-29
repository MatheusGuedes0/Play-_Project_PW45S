package service;
import form.PostForm;
import model.Post;
import model.PostRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService {

    private final PostRepository postRepository;
    private List<Post> posts = new ArrayList<>();

    @Inject
    public PostService(PostRepository postRepository){
       this.postRepository = postRepository;
    }

    public List<Post> getPosts(){
        return postRepository.getAll();
    }

    public Post getPost(int postId) {
        //TODO: load from database
      Optional<Post> requestedPost =  posts.stream()
                .filter(post -> post.getId() == postId)
                .findAny();
            return requestedPost.orElse(null);
    }

    public void add(PostForm postForm){
        //TODO: save into the database
        posts.add(new Post(3,postForm.getTitle(), postForm.getContent()));
    }
}
