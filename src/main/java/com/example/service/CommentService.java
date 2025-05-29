package com.example.service;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * コメントの情報を操作して業務処理を行う.
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * コメント検索を行う.
     *
     * @param articleId 記事ID
     * @return コメント一覧
     */
    public List<Comment> searchByArticleId(int articleId){
        return commentRepository.findByArticleId(articleId);
    }

    /**
     * コメントを挿入する.
     *
     * @param comment コメント
     */
    public void Insert(Comment comment){
        commentRepository.Insert(comment);
    }

    /**
     * 記事IDでコメントを削除する.
     *
     * @param articleId 記事ID
     */
    public void deleteByArticleId(int articleId){
        commentRepository.deleteByArticleId(articleId);
    }


    /**
     * コメントのidでコメントを削除する.
     *
     * @param commentId 記事ID
     */
    public void deleteById(int commentId){
        commentRepository.deleteById(commentId);
    }

}
