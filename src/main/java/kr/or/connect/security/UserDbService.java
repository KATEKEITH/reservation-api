package kr.or.connect.security;

import java.util.List;

import kr.or.connect.security.UserEntity;
import kr.or.connect.security.UserRoleEntity;

public interface UserDbService {

    public UserEntity getUser(String loginUserId);

    public List<UserRoleEntity> getUserRoles(String loginUserId);

}
