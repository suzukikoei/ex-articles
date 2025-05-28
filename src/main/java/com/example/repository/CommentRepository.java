package com.example.repository;

import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * コメントの情報を操作するリポジトリ.
 */
@Repository
public class CommentRepository {
    private static final RowMapper<Comment> COMMENT_ROW_MAPPER =
            (rs, i) -> {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setName(rs.getString("name"));
        comment.setContent(rs.getString("content"));
        comment.setArticleId(rs.getInt("article_id"));
        return comment;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 記事IDで検索する.
     *
     * @param articleId 記事ID
     * @return 記事の一覧
     */
    public List<Comment> findByArticleId(int articleId){
        String sql = """
                SELECT id, name, content, article_id FROM comments WHERE article_id = :articleId ORDER BY id DESC;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
        return template.query(sql, param, COMMENT_ROW_MAPPER);
    }

    /**
     * コメントをDBに挿入する.
     *
     * @param comment コメント
     */
    public void Insert(Comment comment){
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        String sql = """
                INSERT INTO comments (id, name, content, article_id) VALUES (:id, :name, :content, :articleId);
                """;
        template.update(sql, param);
    }

    /**
     * コメントを削除する.
     *
     * @param articleId 記事ID
     */
    public void deleteByArticleId(int articleId){
        String sql = """
                DELETE FROM comments WHERE id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", articleId);
        template.update(sql, param);
    }

}
