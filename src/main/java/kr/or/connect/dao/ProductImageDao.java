package kr.or.connect.dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;
import static kr.or.connect.dao.sql.ReservationSqls.*;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.dto.ProductImage;

@Repository
public class ProductImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
    private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);

    public ProductImageDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("product_image");
    }

    public List<ProductImage> selectById(Integer displayId) {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("displayId", displayId);
        return jdbc.query(SELECT_PRODUCT_IMAGE_BY_ID, params, rowMapper);
    }
}
