package kr.or.connect.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.dao.sql.MemberDaoSqls;
import kr.or.connect.dto.member.Member;

@Repository
public class MemberDao {

    private NamedParameterJdbcTemplate jdbc;

    // BeanPropertyRowMapper는 Role클래스의 프로퍼티를 보고 자동으로 칼럼과 맵핑해주는 RowMapper객체를 생성한다.
    // RowMapper는 한건의 recode를 쉽게 객체에 담아 줄수 있도록 도와준다.
    private RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);

    private MemberDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public Member getMemberByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return jdbc.queryForObject(MemberDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
    }
}
