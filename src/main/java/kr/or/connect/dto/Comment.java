package kr.or.connect.dto;

import java.util.List;

import kr.or.connect.dto.CommentImages;

public class Comment {

    private Long id;
    private Long productId;
    private Long reservationInfoId;
    private int score;
    private String email;
    private String comment;
    private String createDate;
    private String modifyDate;
    private List<CommentImages> reservationUserCommentImages;

    public List<CommentImages> getReservationUserCommentImages() {
        return this.reservationUserCommentImages;
    }

    public void setReservationUserCommentImages(List<CommentImages> reservationUserCommentImages) {
        this.reservationUserCommentImages = reservationUserCommentImages;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getReservationInfoId() {
        return this.reservationInfoId;
    }

    public void setReservationInfoId(Long reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

}
