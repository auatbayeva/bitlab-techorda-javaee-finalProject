package kz.bitlab.javaee.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String comment;
    private Timestamp postDate;
    private User user;
    private News news;

    public Comment() {}
    public Comment(String comment, Timestamp postDate, User user, News news) {
        this.comment = comment;
        this.postDate = postDate;
        this.user = user;
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
