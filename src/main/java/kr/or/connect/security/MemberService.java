package kr.or.connect.security;

import java.util.List;

import kr.or.connect.dto.member.Member;

import kr.or.connect.security.UserEntity;
import kr.or.connect.security.UserRoleEntity;

public interface MemberService extends UserDbService {

    UserEntity getUser(String loginUserId);

    void addMember(Member member, boolean admin);

    public List<UserRoleEntity> getUserRoles(String loginUserId);

    Member getMemberByEmail(String loginUserId);

}
