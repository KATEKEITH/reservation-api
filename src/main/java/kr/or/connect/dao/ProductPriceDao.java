package kr.or.connect.dao;

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

import kr.or.connect.dto.Price;

@Repository
public class ProductPriceDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Price> rowMapper = BeanPropertyRowMapper.newInstance(Price.class);

    public ProductPriceDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("product_price");
    }

    public List<Price> selectById(Integer displayId) {

        Map<String, Integer> map = new HashMap<>();
        map.put("displayId", displayId);
        return jdbc.query(SELECT_PRICE_BY_ID, map, rowMapper);
    }

}
