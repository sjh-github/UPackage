package com.wwdlb.hongruan.model;

public class TaskAndSmallTask {
    private Integer id;

    private Integer taskid;

    private Integer smalltaskid;

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

    public Integer getSmalltaskid() {
        return smalltaskid;
    }

    public void setSmalltaskid(Integer smalltaskid) {
        this.smalltaskid = smalltaskid;
    }

    public TaskAndSmallTask() {
    }

    public TaskAndSmallTask(Integer taskid, Integer smalltaskid) {
        this.taskid = taskid;
        this.smalltaskid = smalltaskid;
    }
}