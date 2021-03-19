package kr.or.connect.dto;

public class MemberRole {

    private Long id;
    private Long memberId;
    private String roleName;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public MemberRole() {

    }

    public MemberRole(Long memberId, String roleName) {
        this.memberId = memberId;
        this.roleName = roleName;
    }
}
