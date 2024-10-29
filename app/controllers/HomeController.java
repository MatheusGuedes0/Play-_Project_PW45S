package controllers;

import form.PostForm;
import model.Post;
import service.PostService;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;

import javax.inject.Inject;
import play.mvc.Http; // Importação correta para Http.Request
import play.mvc.Result; // Importação correta para Result

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
        }

        postService.add(boundForm.get());
        return redirect(routes.HomeController.getPosts(null)); // Passa null para redirecionar corretamente
    }
}
