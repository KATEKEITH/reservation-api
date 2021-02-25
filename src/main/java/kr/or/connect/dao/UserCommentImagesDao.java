package kr.or.connect.dao;

import java.util.List;

import javax.sql.DataSource;
import static kr.or.connect.dao.ReservationSqls.*;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.dto.CommentImages;

@Repository
public class UserCommentImagesDao {
    private NamedParameterJdbcTemplate jdbc;
    RowMapper<CommentImages> rowMapper = BeanPropertyRowMapper.newInstance(CommentImages.class);

    public UserCommentImagesDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<CommentImages> selectAll() {
        return jdbc.query(SELECT_ALL_COMMENT_IMAGES, rowMapper);
    }

}
