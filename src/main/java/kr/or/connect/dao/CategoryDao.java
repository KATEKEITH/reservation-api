package kr.or.connect.dao;

import static kr.or.connect.dao.sql.ReservationSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.dto.Category;

@Repository
public class CategoryDao {

    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

    public CategoryDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("category");
    }

    public List<Category> selectAll() {
        return jdbc.query(SELECT_ALL_CATEGORY, rowMapper);
    }

    public int selectCount() {
        return jdbc.queryForObject(SELECT_CATEGORY_COUNT, Collections.emptyMap(), Integer.class);
    }

}
