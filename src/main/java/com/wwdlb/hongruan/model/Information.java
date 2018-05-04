package com.wwdlb.hongruan.model;

public class Information {
    private Integer inforid;

    private String sendname;

    private String toemail;

    private String content;

    private String time;

    public Integer getInforid() {
        return inforid;
    }

    public void setInforid(Integer inforid) {
        this.inforid = inforid;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname == null ? null : sendname.trim();
    }

    public String getToemail() {
        return toemail;
    }

    public void setToemail(String toemail) {
        this.toemail = toemail == null ? null : toemail.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}