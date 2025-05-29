package com.example.service;

import com.example.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Test
    void searchByArticleId() {
        List<Comment> actualComments = commentService.searchByArticleId(1);
        assertEquals(7, actualComments.size(), "count: 期待された結果と異なります");
    }

    @Test
    void insert() {
        Comment comment = new Comment();
        comment.setName("テスト");
        comment.setContent("テストコメント");
        comment.setArticleId(1);
        commentService.Insert(comment);
        List<Comment> actualComments = commentService.searchByArticleId(1);
        assertEquals(8, actualComments.size(), "count: 期待された結果と異なります");
    }

    @Test
    void deleteByArticleId() {
    }
}