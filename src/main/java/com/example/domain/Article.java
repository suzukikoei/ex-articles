package com.example.domain;


import java.util.List;
/**
 * 記事ドメイン.
 */
public class Article {
    /** 記事ID */
    private Integer id;
    /** 名前　*/
    private String name;
    /** 記事内容*/
    private String content;
    /** コメント　*/
    private List<Comment> comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
