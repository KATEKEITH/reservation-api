package kr.or.connect.service;

import java.util.List;

import kr.or.connect.dto.Comment;

public interface UserCommentService {

    int COMMENT_LIMIT = 5;

    int getCommentCount();

    public List<Comment> getComments(Integer productId, int start);

    int getAvgScore();
}
