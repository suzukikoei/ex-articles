package com.example.repository;

import com.example.domain.Article;
import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 記事の情報を操作するリポジトリ.
 */
@Repository
public class ArticleRepository {
    private static final RowMapper<Article> ARTICLE_ROW_MAPPER =
            (rs, i) -> {
        Article article = new Article();
        article.setId(rs.getInt("id"));
        article.setName(rs.getString("name"));
        article.setContent(rs.getString("content"));
        List<Comment> comments = new ArrayList<>();
        article.setComments(comments);
        return article;
            };
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private CommentRepository commentrepository;

    /**
     * 記事を全件検索する.
     *
     * @return 記事の一覧
     */
    public List<Article> findAll(){
//        String sql = """
//                SELECT
//                A.id, A.name, A.content
//                C.id, C.name, C.content, C.article_id
//                FROM articles AS A
//                right outer join
//                comments AS C
//                on
//                A.id = C.article_id
//                ORDER BY A.id;
//                """;


        String sqlForArticles = """
                SELECT id, name, content FROM articles ORDER BY id;
                """;
        List<Article> articles = template.query(sqlForArticles, ARTICLE_ROW_MAPPER);
        for(Article article: articles){
            article.setComments(commentrepository.findByArticleId(article.getId()));
        }
        return articles;
    }

    /**
     * 記事をDBに挿入する.
     * @param article 記事
     */
    public void InsertArticle(Article article){
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        String sql = """
                INSERT INTO articles (id, name, content) VALUES (:id, :name, :content);
                """;
        template.update(sql, param);
    }

    /**
     * 記事を削除する.
     *
     * @param id ID
     */
    public void DeleteByID(int id){
        String sql = """
                DELETE FROM articles WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }




}
