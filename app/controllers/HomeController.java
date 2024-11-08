package controllers;

import form.PostForm;
import model.Post;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import service.PostService;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class HomeController extends Controller {

    private final Form<PostForm> postForm;
    private final PostService postService;

    @Inject
    public HomeController(FormFactory formFactory, PostService postService) {
        this.postForm = formFactory.form(PostForm.class);
        this.postService = postService;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result getPosts(Integer postId, Http.Request request) { // Mantém o request como parâmetro
        if (postId == null) {
            return ok(views.html.posts.render(postService.getPosts(), postForm, request));
        }

        Post post = postService.getPost(postId);
        if (post == null) {
            return ok(views.html.posts.render(Collections.emptyList(), postForm, request));
        }

        return ok(views.html.posts.render(List.of(post), postForm, request));
    }

    public Result createPost(Http.Request request) { // Recebe o request como parâmetro
        Form<PostForm> boundForm = postForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            return badRequest(views.html.posts.render(postService.getPosts(), boundForm, request));
        } else {
            postService.add(boundForm.get());
            return redirect(routes.HomeController.getPosts(null)); 
        }
    }

    public Result editPost(Integer id, Http.Request request) {
        // Bind the form data from the request
        Form<PostForm> boundForm = postForm.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            // Render the edit page with the original post data if there are form errors
            Post post = postService.getPost(id);
            return ok(views.html.editPosts.render(List.of(post), boundForm, request));
        }

        // Get the updated data from the form and update the post
        PostForm postFormData = boundForm.get();
        boolean updated = postService.updatePost(id, postFormData);

        if (!updated) {
            // Handle case where the post with the given ID was not found
            return notFound("Post not found");
        }

        // Redirect to the posts page after a successful update
        return redirect(routes.HomeController.getPosts(null));
    }

    public Result deletePost(Integer id, Http.Request request) {
        Post post = postService.getPost(id);
        if (post == null) {
            return ok(views.html.posts.render(Collections.emptyList(), postForm, request));
        }
        postService.delete(id); // Passa o ID diretamente
        return redirect(routes.HomeController.getPosts(null));
    }
}
