package form;

import play.data.validation.Constraints;

import javax.validation.Constraint;

public class PostForm {
    @Constraints.Required

    private String title;

    @Constraints.Required
    @Constraints.MaxLength(200)
    private String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
