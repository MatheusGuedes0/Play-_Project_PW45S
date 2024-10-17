package model;

public class Post {
    private int id;
    private String title;
    private String content;


    public Post() {
    }

    public Post(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public int getId(){
        return id;
    }

    public String getContent() {
        return content;
    }


}
