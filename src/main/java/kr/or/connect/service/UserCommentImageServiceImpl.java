package kr.or.connect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.dao.UserCommentImagesDao;
import kr.or.connect.dto.CommentImages;

@Service
public class UserCommentImageServiceImpl implements UserCommentImageService {

    private final UserCommentImagesDao userCommentImagesDao;

    public UserCommentImageServiceImpl(UserCommentImagesDao userCommentImagesDao) {
        this.userCommentImagesDao = userCommentImagesDao;
    }

    @Override
    public List<CommentImages> getCommentImages() {
        List<CommentImages> list = userCommentImagesDao.selectAll();
        return list;
    }

}
