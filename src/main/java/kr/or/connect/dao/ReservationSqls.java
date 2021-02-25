package kr.or.connect.dao;

public class ReservationSqls {

    public static final String SELECT_CATEGORY_COUNT = "SELECT COUNT(*) FROM CATEGORY";
    public static final String SELECT_ALL_CATEGORY = "SELECT C.ID, C.NAME, COUNT(CATEGORY_ID) AS COUNT FROM PRODUCT P INNER JOIN CATEGORY C ON C.ID = P.CATEGORY_ID GROUP BY C.ID";

    public static final String SELECT_ALL_PRODUCT = "SELECT P.ID, P.CATEGORY_ID, D.ID AS DISPLAYINFOID, C.NAME, P.DESCRIPTION, P.CONTENT, P.EVENT, D.OPENING_HOURS, D.PLACE_NAME, D.PLACE_LOT, D.PLACE_STREET, D.TEL, D.HOMEPAGE, D.EMAIL, D.CREATE_DATE, D.MODIFY_DATE, I.FILE_ID FROM PRODUCT P INNER JOIN CATEGORY C ON C.ID = P.CATEGORY_ID INNER JOIN DISPLAY_INFO D ON P.ID = D.PRODUCT_ID INNER JOIN PRODUCT_IMAGE I ON P.ID = I.PRODUCT_ID AND I.TYPE = 'MA'";
    public static final String SELECT_DISPLAYINFO_BY_DISPLAY_ID = "SELECT P.ID, P.CATEGORY_ID, D.ID AS DISPLAYINFOID, C.NAME, P.DESCRIPTION, P.CONTENT, P.EVENT, D.OPENING_HOURS, D.PLACE_NAME, D.PLACE_LOT, D.PLACE_STREET, D.TEL, D.HOMEPAGE, D.EMAIL, D.CREATE_DATE, D.MODIFY_DATE, I.FILE_ID FROM PRODUCT P INNER JOIN CATEGORY C ON C.ID = P.CATEGORY_ID INNER JOIN DISPLAY_INFO D ON P.ID = D.PRODUCT_ID INNER JOIN PRODUCT_IMAGE I ON P.ID = I.PRODUCT_ID AND I.TYPE = 'MA' WHERE D.ID =:displayId";
    public static final String SELECT_DISPLAYINFO_BY_CATEGORY_ID = "SELECT P.ID, P.CATEGORY_ID, D.ID AS DISPLAYINFOID, C.NAME, P.DESCRIPTION, P.CONTENT, P.EVENT, D.OPENING_HOURS, D.PLACE_NAME, D.PLACE_LOT, D.PLACE_STREET, D.TEL, D.HOMEPAGE, D.EMAIL, D.CREATE_DATE, D.MODIFY_DATE, I.FILE_ID FROM PRODUCT P INNER JOIN CATEGORY C ON C.ID = P.CATEGORY_ID INNER JOIN DISPLAY_INFO D ON P.ID = D.PRODUCT_ID INNER JOIN PRODUCT_IMAGE I ON P.ID = I.PRODUCT_ID AND I.TYPE = 'MA' WHERE P.CATEGORY_ID IN (:categoryId) ORDER BY P.CATEGORY_ID =:categoryId DESC limit :start, :limit";
    public static final String SELECT_ALL_DISPLAYINFO_COUNT_BY_ID = "SELECT COUNT(*) FROM PRODUCT P INNER JOIN CATEGORY C ON C.ID = P.CATEGORY_ID INNER JOIN DISPLAY_INFO D ON P.ID = D.PRODUCT_ID INNER JOIN PRODUCT_IMAGE I ON P.ID = I.PRODUCT_ID AND I.TYPE = 'MA'";
    public static final String SELECT_DISPLAYINFO_COUNT_BY_ID = "SELECT COUNT(*) FROM PRODUCT P INNER JOIN CATEGORY C ON C.ID = P.CATEGORY_ID INNER JOIN DISPLAY_INFO D ON P.ID = D.PRODUCT_ID INNER JOIN PRODUCT_IMAGE I ON P.ID = I.PRODUCT_ID AND I.TYPE = 'MA' WHERE P.CATEGORY_ID IN (:categoryId) ORDER BY P.CATEGORY_ID DESC";

    public static final String SELECT_PROMOTION_COUNT = "SELECT COUNT(*) FROM PROMOTION";
    public static final String SELECT_ALL_PROMOTION = "SELECT M.ID, M.PRODUCT_ID, P.CATEGORY_ID, C.NAME AS CATEGORYNAME, P.DESCRIPTION, I.FILE_ID FROM PRODUCT P INNER JOIN PROMOTION M ON P.ID = M.PRODUCT_ID INNER JOIN CATEGORY C ON P.CATEGORY_ID = C.ID INNER JOIN PRODUCT_IMAGE I ON P.ID = I.PRODUCT_ID WHERE TYPE='MA'";

    public static final String SELECT_PRODUCT_IMAGE_BY_ID = "SELECT P.product_id, P.id AS product_Image_Id, P.type, P.file_id AS file_info_id ,F.file_name, F.save_file_name, F.content_type, F.delete_flag, F.create_date, F.modify_date FROM product_image P INNER JOIN file_info F ON F.id = P.file_id WHERE P.product_id = (SELECT D.product_id FROM display_info D WHERE D.id =:displayId) AND P.type = 'ma'";
    public static final String SELECT_DISPLAYINFO_IMAGE_BY_ID = "SELECT D.id, D.display_info_id, F.id AS file_id, F.file_name, F.save_file_name, F.content_type, F.delete_flag, F.create_date, F.modify_date FROM display_info_image D INNER JOIN file_info F ON F.id = D.file_id WHERE D.id =:displayId";
    public static final String SELECT_COMMENT_AVG = "SELECT TRUNCATE(AVG(SCORE), 0) FROM reservation_user_comment C";
    public static final String SELECT_PRICE_BY_ID = "SELECT * FROM product_price P WHERE P.product_id = (SELECT D.product_id FROM display_info D WHERE D.id =:displayId)";

    // public static final String SELECT_ALL_COMMENT_IMAGES = "SELECT I.id,
    // I.reservation_user_comment_id, I.file_id FROM reservation_user_comment R
    // INNER JOIN reservation_user_comment_image I ON R.reservation_info_id =
    // I.reservation_info_id";

    public static final String SELECT_ALL_COMMENT_IMAGES = "SELECT * FROM reservation_user_comment_image";
    public static final String SELECT_ALL_COMMENT_COUNT = "SELECT COUNT(*) FROM reservation_user_comment";
    public static final String SELECT_ALL_COMMENTS = "SELECT C.id, C.product_id, C.reservation_info_id, C.SCORE, U.email , C.comment, C.create_date, C.modify_date, I.file_id FROM reservation_user_comment C INNER JOIN user U ON C.USER_ID = U.ID LEFT JOIN reservation_user_comment_image I ON C.reservation_info_id = I.reservation_info_id WHERE C.product_id IN (:productId) ORDER BY C.product_id =:productId LIMIT :start, :limit";
}
