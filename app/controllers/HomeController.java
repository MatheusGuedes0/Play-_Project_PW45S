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

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Form<PostForm> postForm;
    private final PostService postService;
    @Inject
    public HomeController(FormFactory formFactory, PostService postService){
        this.postForm = formFactory.form(PostForm.class);
        this.postService = postService;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result getPosts(Integer postId, Http.Request request){

        if(postId ==null){
            return ok(views.html.posts.render(postService.getPosts(),
                    postForm,
                    request));
        }
        Post post = postService.getPost(postId);
        if(post ==null){
            return ok(views.html.posts.render(Collections.emptyList(),postForm, request));
        }
        return ok(views.html.posts.render(List.of(post),postForm, request));

    }

  public Result createPost(Http.Request request) {
      Form<PostForm> boundForm = postForm.bindFromRequest(request);

          if (boundForm.hasErrors()) {
              return badRequest(views.html.posts.render(postService.getPosts(), boundForm, request));
          }
          postService.add(boundForm.get());
          return redirect(routes.HomeController.getPosts(null));
      }
  }
