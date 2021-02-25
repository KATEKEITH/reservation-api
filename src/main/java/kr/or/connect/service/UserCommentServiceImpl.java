package kr.or.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.dao.UserCommentDao;
import kr.or.connect.dto.Comment;

@Service
public class UserCommentServiceImpl implements UserCommentService {

    private final UserCommentDao userCommentDao;

    public UserCommentServiceImpl(UserCommentDao userCommentDao) {
        this.userCommentDao = userCommentDao;
    }

    @Override
    public int getAvgScore() {
        return userCommentDao.calculateAvg();
    }

    @Override
    public int getCommentCount() {
        return userCommentDao.selectCount();
    }

    @Override
    public List<Comment> getComments(Integer productId, int start) {
        List<Comment> list = userCommentDao.selectAll(productId, start, UserCommentService.COMMENT_LIMIT);
        return list;
    }
}
