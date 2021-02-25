package kr.or.connect.dto;

public class CommentImages {

    private Long id;
    private Long reservationInfoId;
    private Long reservation_user_comment_id;
    private Long fileId;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationInfoId() {
        return this.reservationInfoId;
    }

    public void setReservationInfoId(Long reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public Long getReservation_user_comment_id() {
        return this.reservation_user_comment_id;
    }

    public void setReservation_user_comment_id(Long reservation_user_comment_id) {
        this.reservation_user_comment_id = reservation_user_comment_id;
    }

    public Long getFileId() {
        return this.fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

}
