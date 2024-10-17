package controllers;

import model.Post;
import play.mvc.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result getPosts(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("PW45S","teste para o seminário de PW45S"));
        posts.add(new Post("Play!","Play! framework"));
        return ok(views.html.posts.render(posts));
    }

}
