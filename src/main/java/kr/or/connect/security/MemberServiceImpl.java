package kr.or.connect.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.dao.MemberDao;
import kr.or.connect.dao.MemberRoleDao;

import kr.or.connect.security.UserEntity;
import kr.or.connect.security.UserRoleEntity;

import kr.or.connect.dto.member.Member;
import kr.or.connect.dto.member.MemberRole;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final MemberRoleDao memberRoleDao;

    public MemberServiceImpl(MemberDao memberDao, MemberRoleDao memberRoleDao) {
        this.memberDao = memberDao;
        this.memberRoleDao = memberRoleDao;
    }

    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
        Member member = memberDao.getMemberByEmail(loginUserId);
        return new UserEntity(member.getEmail(), member.getPassword());
    }

    @Override
    @Transactional(readOnly = false)
    public void addMember(Member member, boolean admin) {

        memberDao.addMember(member);

        Member seletedMember = memberDao.getMemberByEmail(member.getEmail());
        Long memberId = seletedMember.getId();

        if (admin) {
            memberRoleDao.addAdminRole(memberId);
        }
        memberRoleDao.addUserRole(memberId);

    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {

        List<MemberRole> memberRoles = memberRoleDao.getRolesByEmail(loginUserId);

        List<UserRoleEntity> list = new ArrayList<>();

        for (MemberRole memberRole : memberRoles) {
            list.add(new UserRoleEntity(loginUserId, memberRole.getRoleName()));
        }
        return list;
    }

    @Override
    public Member getMemberByEmail(String email) {
        return memberDao.getMemberByEmail(email);
    }

}
