package com.wwdlb.hongruan.model;

public class PhasePlan {
    private Integer phaseplanid;

    private String content;

    private String havefinished;

    private String finishtime;

    public Integer getPhaseplanid() {
        return phaseplanid;
    }

    public void setPhaseplanid(Integer phaseplanid) {
        this.phaseplanid = phaseplanid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getHavefinished() {
        return havefinished;
    }

    public void setHavefinished(String havefinished) {
        this.havefinished = havefinished == null ? null : havefinished.trim();
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime == null ? null : finishtime.trim();
    }
}