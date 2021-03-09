package kr.or.connect.security;

public class UserRoleEntity {

    private String userLoginId;
    private String roleName;

    public UserRoleEntity(String userLoginId, String roleName) {
        this.userLoginId = userLoginId;
        this.roleName = roleName;
    }

    public String getUserLoginId() {
        return this.userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
