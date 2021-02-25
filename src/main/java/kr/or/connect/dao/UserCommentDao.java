package kr.or.connect.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import static kr.or.connect.dao.ReservationSqls.*;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.dto.Comment;

@Repository
public class UserCommentDao {

    private NamedParameterJdbcTemplate jdbc;
    RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
    private SimpleJdbcInsert insertAction;

    public UserCommentDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_user_comment");
    }

    public List<Comment> selectAll(Integer productId, int start, int limit) {

        Map<String, Integer> params = new HashMap<>();
        params.put("productId", productId);
        params.put("start", start);
        params.put("limit", limit);

        return jdbc.query(SELECT_ALL_COMMENTS, params, rowMapper);
    }

    public int selectCount() {
        return jdbc.queryForObject(SELECT_ALL_COMMENT_COUNT, Collections.emptyMap(), Integer.class);
    }

    public int calculateAvg() {
        return jdbc.queryForObject(SELECT_COMMENT_AVG, Collections.emptyMap(), Integer.class);
    }
}
