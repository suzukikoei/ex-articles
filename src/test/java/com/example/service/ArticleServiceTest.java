package com.example.service;

import com.example.domain.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    void searchAll() {
        List<Article> actualArticles = articleService.searchAll();
        assertEquals(4,actualArticles.size(), "count: 期待された長さと異なります");
    }

    @Test
    void insertArticle() {
        Article article = new Article();
        article.setName("テスト");
        article.setContent("テストのコメント");
        article.setComments(new ArrayList<>());
        articleService.InsertArticle(article);
        List<Article> actualArticles = articleService.searchAll();
        assertEquals(5,actualArticles.size(), "count: 期待された長さと異なります");
    }

    @Test
    void deleteArticle(){
        articleService.DeleteById(1);
        List<Article> actualArticles = articleService.searchAll();
        assertEquals(3,actualArticles.size(), "count: 期待された長さと異なります");
    }

}