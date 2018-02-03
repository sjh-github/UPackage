package com.wwdlb.hongruan.model;

public class TaskAndPhasePlan {
    private Integer id;

    private Integer taskid;

    private Integer phaseplanid;

    private Integer phasenum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getPhaseplanid() {
        return phaseplanid;
    }

    public void setPhaseplanid(Integer phaseplanid) {
        this.phaseplanid = phaseplanid;
    }

    public Integer getPhasenum() {
        return phasenum;
    }

    public void setPhasenum(Integer phasenum) {
        this.phasenum = phasenum;
    }
}