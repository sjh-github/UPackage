package com.wwdlb.hongruan.pojo;

import com.wwdlb.hongruan.model.CustomProgress;

public class CustomProgressPojo {
    private CustomProgress customProgress;
    private Integer progressOrder;

    public CustomProgress getCustomProgress() {
        return customProgress;
    }

    public void setCustomProgress(CustomProgress customProgress) {
        this.customProgress = customProgress;
    }

    public Integer getProgressOrder() {
        return progressOrder;
    }

    public void setProgressOrder(Integer progressOrder) {
        this.progressOrder = progressOrder;
    }

    @Override
    public String toString() {
        return "CustomProgressPojo{" +
                "customProgress=" + customProgress.toString() +
                ", progressOrder=" + progressOrder +
                '}';
    }
}
