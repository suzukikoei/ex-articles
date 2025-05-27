package com.example.service;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 記事の情報を操作して業務処理を行う.
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 記事の全件検索を行う.
     *
     * @return 記事の一覧
     */
    public List<Article> searchAll(){
        return articleRepository.findAll();
    }

    /**
     * 記事を挿入する.
     *
     * @param article 記事
     */
    public void InsertArticle(Article article){
        articleRepository.InsertArticle(article);
    }

    /**
     * 記事を削除する.
     *
     * @param id ID
     */
    public void DeleteById(int id){
        articleRepository.DeleteByID(id);
    }

}
