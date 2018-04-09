package com.wwdlb.hongruan.api;

import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.model.SmallTaskAndNumberProgress;
import com.wwdlb.hongruan.model.Task;
import com.wwdlb.hongruan.pojo.CustomProgressPojo;
import com.wwdlb.hongruan.pojo.SmallTaskDetailPojo;
import com.wwdlb.hongruan.service.serviceImpl.GetSmallTaskByIDServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.SmallTaskAndProgressServiceImpl;
import com.wwdlb.hongruan.service.serviceImpl.SmallTaskAndTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Android获取小任务接口
 */
@RestController
public class SmallTaskDetailAction {

    @Autowired
    private GetSmallTaskByIDServiceImpl getSmallTaskByIDServiceImpl;

    @Autowired
    private SmallTaskAndTaskServiceImpl smallTaskAndTaskServiceImpl;

    @Autowired
    private SmallTaskAndProgressServiceImpl smallTaskAndProgressServiceImpl;

    /**
     * 获取小任务
     * @param smallTaskID 小任务ID
     * @return 小任务/NULL
     */
    @GetMapping(value = "/api/smallTask/{smallTaskID}")
    public SmallTaskDetailPojo getSmallTaskByID(@PathVariable Integer smallTaskID){
        SmallTaskDetailPojo smallTaskDetailPojo = new SmallTaskDetailPojo();
        SmallTask smallTask = getSmallTaskByIDServiceImpl.getSmallTaskByID(smallTaskID);

        smallTaskDetailPojo.setSmalltaskid(smallTask.getSmalltaskid());
        smallTaskDetailPojo.setSmalltaskname(smallTask.getSmalltaskname());
        smallTaskDetailPojo.setSmalltaskdetail(smallTask.getSmalltaskdetail());
        smallTaskDetailPojo.setEndtime(smallTask.getEndtime());
        smallTaskDetailPojo.setHavefinished(smallTask.getHavefinished());
        smallTaskDetailPojo.setFinishtime(smallTask.getFinishtime());

        Task task = smallTaskAndTaskServiceImpl.getTaskBySmallTaskID(smallTaskID);
        if (task != null) {
            smallTaskDetailPojo.setSafetyGrade(task.getSafetygrade());
            smallTaskDetailPojo.setPriority(task.getPriority());
        }

        SmallTaskAndNumberProgress smallTaskAndNumberProgress = smallTaskAndProgressServiceImpl.getSmallTaskAndNumberProgressBySmallTaskID(smallTaskID);
        //无数量指标
        if (smallTaskAndNumberProgress == null) {
            ArrayList<CustomProgressPojo> customProgressPojos = smallTaskAndProgressServiceImpl.getSmallTaskAndCustomProgressBySmallTaskID(smallTaskID);
            smallTaskDetailPojo.setCustomProgressPojos(customProgressPojos);
            smallTaskDetailPojo.setSmallTaskAndNumberProgress(null);
        } else {
            //数量指标
            smallTaskDetailPojo.setSmallTaskAndNumberProgress(smallTaskAndNumberProgress);
            smallTaskDetailPojo.setCustomProgressPojos(null);
        }

        return smallTaskDetailPojo;
    }
}
