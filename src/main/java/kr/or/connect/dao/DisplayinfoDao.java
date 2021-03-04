package kr.or.connect.dao;

import static kr.or.connect.dao.sql.ReservationSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.dto.Displayinfo;

@Repository
public class DisplayinfoDao {

    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<Displayinfo> rowMapper = BeanPropertyRowMapper.newInstance(Displayinfo.class);

    public DisplayinfoDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("product");
    }

    public List<Displayinfo> selectAll(Integer categoryId, Integer start, Integer limit) {

        System.out.println(">>>> selectAll:categoryId" + categoryId.getClass().getName());

        Map<String, Integer> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("start", start);
        params.put("limit", limit);

        if (categoryId == null || categoryId == 0) {
            return jdbc.query(SELECT_ALL_PRODUCT, params, rowMapper);
        } else {
            return jdbc.query(selectByCategoryId(), params, rowMapper);
        }
    }

    public Displayinfo selectProductById(Integer displayId) {
        return jdbc.queryForObject(SELECT_DISPLAYINFO_BY_DISPLAY_ID, new MapSqlParameterSource("displayId", displayId),
                rowMapper);
    }

    public int selectCount(Integer categoryId) {
        // SqlParameterSource namedParameters = new
        // MapSqlParameterSource().addValue("categoryId", categoryId);

        if (categoryId == null || categoryId == 0) {
            return jdbc.queryForObject(SELECT_ALL_DISPLAYINFO_COUNT_BY_ID,
                    new MapSqlParameterSource("categoryId", categoryId), Integer.class);
        } else {
            return jdbc.queryForObject(SELECT_DISPLAYINFO_COUNT_BY_ID,
                    new MapSqlParameterSource("categoryId", categoryId), Integer.class);
        }
    }
}
