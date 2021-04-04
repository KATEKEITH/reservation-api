package kr.or.connect.dao.sql;

public class MemberRoleDaoSqls {
        public static final String SELECT_ALL_BY_EMAIL = "SELECT mr.id, mr.member_id, mr.role_name FROM member_role mr JOIN member m ON mr.member_id = m.id WHERE m.email = :email";

        public static final String INSERT_ADMIN_ROLE() {
                return new StringBuilder().append("INSERT INTO member_role(member_id, role_name)")
                                .append("VALUES (:memberId, \"ROLE_ADMIN\")").toString();
        }

        public static final String INSERT_USER_ROLE() {
                return new StringBuilder().append("INSERT INTO member_role(member_id, role_name)")
                                .append("VALUES (:memberId, \"ROLE_USER\")").toString();
        }

}
