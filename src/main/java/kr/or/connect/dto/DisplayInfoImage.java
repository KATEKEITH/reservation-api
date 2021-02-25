package kr.or.connect.dto;

public class DisplayInfoImage {

    private Long id;
    private Long displayInfoId;
    private Long fileId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private int deleteFlag;
    private String createDate;
    private String modifyDate;

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDisplayInfoId() {
        return this.displayInfoId;
    }

    public void setDisplayInfoId(Long displayInfoId) {
        this.displayInfoId = displayInfoId;
    }

    public Long getFileId() {
        return this.fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSaveFileName() {
        return this.saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
