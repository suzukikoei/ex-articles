package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 記事のフォーム.
 */
public class ArticleForm {
    /** 名前　*/
    @NotBlank(message = "投稿者名を入力してください")
    @Size(max = 50, message = "投稿者名は50文字以内で入力してください")
    private String articleName;
    /** 記事内容*/
    @NotBlank(message = "投稿内容を入力してください")
    @Size(max=100,  message = "投稿内容は100文字以内で入力して下さい")
    private String articleContent;

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}
