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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 記事を全件検索する.
     *
     * @return 記事の一覧
     */
//    public List<Article> findAll(){
//
//
//        String sqlForArticles = """
//                SELECT id, name, content FROM articles ORDER BY id DESC;
//                """;
//        List<Article> articles = template.query(sqlForArticles, ARTICLE_ROW_MAPPER);
//        return articles;
//    }

//    public List<Article> findAll(){
//        String sql = """
//        SELECT
//        A.id AS article_id, A.name AS article_name, A.content AS article_content,
//        C.id AS comment_id, C.name AS comment_name, C.content AS comment_content, C.article_id AS comment_for_article_id
//        FROM articles AS A
//        LEFT OUTER JOIN
//        comments AS C
//        ON
//        A.id = C.article_id
//        ORDER BY A.id DESC;
//        """;
//        List<Article> articles = template.query(sql, rs -> {
//            Map<Integer, Article> articleMap = new LinkedHashMap<>();//順序を持つ key->記事ID value->記事
////            List<Comment> commentList = null;
//            while (rs.next()) {
//                int articleId = rs.getInt("article_id");
//                Article article = articleMap.get(articleId);
//                if (article == null) {
//                    article = new Article();
//                    article.setId(articleId);
//                    article.setName(rs.getString("article_name"));
//                    article.setContent(rs.getString("article_content"));
//                    articleMap.put(articleId, article);
////                    commentList = new ArrayList<>();
////                    article.setComments(commentList);
//                    System.out.println(article.toString());
//                }else{
//                    Comment comment = new Comment();
//                    comment.setId(rs.getInt("comment_id"));
//                    comment.setName(rs.getString("comment_name"));
//                    comment.setContent(rs.getString("comment_content"));;
//                    article.getComments().add(comment);
//                }
//
//                int commentId = rs.getInt("comment_for_article_id"); //コメントがないとarticleIdがnulｌで0になる
//                if(article.getComments() == null){//記事はあるけどコメントはない
//                    Comment comment = new Comment();
//                    comment.setId(rs.getInt("comment_id"));
//                    comment.setName(rs.getString("comment_name"));
//                    comment.setContent(rs.getString("comment_content"));
//                    comment.setArticleId(commentId);
//                    List<Comment> commentList = new ArrayList<>();
//                    article.setComments(commentList);
//                    article.getComments().add(comment);
//                    System.out.println(comment.toString());
//                }
//            }
//            System.out.println(articleMap.toString());
//
//            return new ArrayList<>(articleMap.values());
//        });
//        return articles;
//    }

    public List<Article> findAll(){
        String sql = """
    SELECT
    A.id AS article_id, A.name AS article_name, A.content AS article_content,
    C.id AS comment_id, C.name AS comment_name, C.content AS comment_content, C.article_id AS comment_for_article_id
    FROM articles AS A
    LEFT OUTER JOIN comments AS C ON A.id = C.article_id
    ORDER BY A.id DESC, C.id ASC;
    """;

        List<Article> articles = template.query(sql, rs -> {
            Map<Integer, Article> articleMap = new LinkedHashMap<>();
            while (rs.next()) {
                int articleId = rs.getInt("article_id");
                Article article = articleMap.get(articleId);
                if (article == null) {
                    article = new Article();
                    article.setId(articleId);
                    article.setName(rs.getString("article_name"));
                    article.setContent(rs.getString("article_content"));
                    article.setComments(new ArrayList<>()); // コメントリストを初期化
                    articleMap.put(articleId, article);
                }

                int commentId = rs.getInt("comment_id");
                // コメントが存在する場合だけ追加
                if (!rs.wasNull()) {
                    Comment comment = new Comment();
                    comment.setId(commentId);
                    comment.setName(rs.getString("comment_name"));
                    comment.setContent(rs.getString("comment_content"));
                    comment.setArticleId(articleId);
                    article.getComments().add(comment);
                }
            }
            return new ArrayList<>(articleMap.values());
        });

        return articles;
    }

    /**
     * 記事をDBに挿入する.
     * @param article 記事
     */
    public void InsertArticle(Article article){
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        String sql = """
                INSERT INTO articles (name, content) VALUES (:name, :content);
                """;
        template.update(sql, param);
    }

    /**
     * 記事を削除する.
     *
     * @param id ID
     */
//    public void DeleteByID(int id){
//        String sql = """
//                DELETE FROM articles WHERE id = :id;
//                """;
//        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
//        template.update(sql, param);
//    }

    public void DeleteByID(int id){
        String sql = """
                DELETE FROM articles WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        template.update(sql, param);
    }




}
