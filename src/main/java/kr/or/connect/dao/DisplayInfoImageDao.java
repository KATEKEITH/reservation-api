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

import kr.or.connect.dto.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

    public DisplayInfoImageDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("display_info_image");
    }

    public List<DisplayInfoImage> selectById(Integer displayId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.query(SELECT_DISPLAYINFO_IMAGE_BY_ID, params, rowMapper);
    }
}
