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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public String index(ArticleForm articleForm, CommentForm commentForm, Model model) {
        List<Article> articles = articleService.searchAll();
        model.addAttribute("articles", articles);
        return "bulletin-board";
    }

    @PostMapping("/postComment")
    public String postComment(ArticleForm articleForm, CommentForm commentForm, Model model){
        ModelMapper modelMapper = new ModelMapper();
        Comment comment = modelMapper.map(commentForm,Comment.class);
        commentService.Insert(comment);
        return index(articleForm, commentForm, model);
    }

}
