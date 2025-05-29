package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * コメントのフォーム.
 */
public class CommentForm {
    /** 名前　*/
    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50文字以内で入力してください")
    private String commentName;
    /** コメント内容　*/
    @NotBlank(message = "コメントを入力してください")
    @Size(max = 100, message = "コメントは100文字以内で入力してください")
    private String commentContent;
    /** 記事ID */
    private String articleId;

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
