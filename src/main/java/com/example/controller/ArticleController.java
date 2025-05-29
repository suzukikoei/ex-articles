package com.example.controller;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 記事の情報を操作するコントローラ.
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    /**
     * 記事一覧ページを表示する.
     *
     * @param articleForm 記事のフォーム
     * @param commentForm 　コメントのフォーム
     * @param model       　リクエストスコープ
     * @return　記事一覧
     */
    @GetMapping("")
    public String index(ArticleForm articleForm, CommentForm commentForm, Model model) {
        List<Article> articles = articleService.searchAll();
        model.addAttribute("articles", articles);
        return "bulletin-board";
    }

    /**
     * 記事を投稿する.
     *
     * @param articleForm 記事のフォーム
     * @param commentForm 　コメントのフォーム
     * @param model       　リクエストスコープ
     * @return 記事一覧
     */
    @PostMapping("/postPost")
    public String postPost(
            @Validated ArticleForm articleForm
            , BindingResult result
            , CommentForm commentForm
            , Model model) {
        if (result.hasErrors()) {
            return index(articleForm, commentForm, model);
        }
        ModelMapper modelMapper = new ModelMapper();
        Article article = modelMapper.map(articleForm, Article.class);
        articleService.InsertArticle(article);
        return "redirect:/articles";
    }

    /**
     * コメントを投稿する.
     *
     * @param articleForm 記事のフォーム
     * @param commentForm 　コメントのフォーム
     * @param model       　リクエストスコープ
     * @return 記事一覧
     */
    @PostMapping("/postComment")
    public String postComment(
            ArticleForm articleForm,
            @Validated CommentForm commentForm,
            BindingResult result
            , Model model) {
        if (result.hasErrors()) {
            return index(articleForm, commentForm, model);
        }
//        int commentArticleId = commentForm.getArticleId();
//        model.addAttribute("commentArticleId", commentArticleId);
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(commentForm, Comment.class);
        commentService.Insert(comment);
        return "redirect:/articles";
    }

    /**
     * 記事とコメントを削除する.
     *
     * @param articleForm 記事のフォーム
     * @param commentForm 　コメントのフォーム
     * @param model       　リクエストスコープ
     * @return 記事一覧
     */
    @PostMapping("/delete")
    public String delete(Integer articleId, ArticleForm articleForm, CommentForm commentForm, Model model) {
//        commentService.deleteByArticleId(articleId);
        articleService.DeleteById(articleId);
        return "redirect:/articles";
    }

    @PostMapping("/deleteComment")
    public String deleteComment(Integer commentId, ArticleForm articleForm, CommentForm commentForm, Model model){
        commentService.deleteById(commentId);
        return "redirect:/articles";
    }
}
