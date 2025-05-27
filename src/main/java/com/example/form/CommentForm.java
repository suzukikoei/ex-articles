package com.example.form;

/**
 * コメントのフォーム.
 */
public class CommentForm {
    /** 名前　*/
    private String name;
    /** コメント内容　*/
    private String content;

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
}
