package kr.or.connect.dao;

import static kr.or.connect.dao.sql.ReservationSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.dto.Promotion;

@Repository
public class PromotionDao {

    NamedParameterJdbcTemplate jdbc;
    RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

    public PromotionDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Promotion> selectAll() {
        return jdbc.query(SELECT_ALL_PROMOTION, rowMapper);
    }

    public int selectCount() {
        return jdbc.queryForObject(SELECT_PROMOTION_COUNT, Collections.emptyMap(), Integer.class);
    }
}
