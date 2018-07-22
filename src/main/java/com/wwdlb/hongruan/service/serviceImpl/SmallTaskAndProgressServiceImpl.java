package com.wwdlb.hongruan.service.serviceImpl;

import com.wwdlb.hongruan.mapper.CustomProgressMapper;
import com.wwdlb.hongruan.mapper.SmallTaskAndCustomProgressMapper;
import com.wwdlb.hongruan.mapper.SmallTaskAndNumberProgressMapper;
import com.wwdlb.hongruan.model.CustomProgress;
import com.wwdlb.hongruan.model.SmallTaskAndCustomProgress;
import com.wwdlb.hongruan.model.SmallTaskAndNumberProgress;
import com.wwdlb.hongruan.pojo.CustomProgressPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 小任务和指标服务实例
 */
@Service
public class SmallTaskAndProgressServiceImpl {
   @Autowired
    private SmallTaskAndNumberProgressMapper smallTaskAndNumberProgressMapper;

   @Autowired
    private SmallTaskAndCustomProgressMapper smallTaskAndCustomProgressMapper;

   @Autowired
   private CustomProgressMapper customProgressMapper;

    /**
     * 获取小任务数量指标
     * @param smallTaskID 小任务ID
     * @return 小任务及其数量指标相关信息/NULL
     */
   public SmallTaskAndNumberProgress getSmallTaskAndNumberProgressBySmallTaskID(Integer smallTaskID) {
       return smallTaskAndNumberProgressMapper.selectBySmallTaskID(smallTaskID);
   }

    /**
     * 获取小任务自定义指标
     * @param smallTaskID 小任务ID
     * @return 自定义指标/NULL
     */
   public ArrayList<CustomProgressPojo> getSmallTaskAndCustomProgressBySmallTaskID(Integer smallTaskID) {
       ArrayList<SmallTaskAndCustomProgress> smallTaskAndCustomProgressArrayList = smallTaskAndCustomProgressMapper.getSmallTaskAndCustomProgressBySmallTaskID(smallTaskID);
       if (smallTaskAndCustomProgressArrayList == null) {
           return null;
       }
       CustomProgressPojo customProgressPojo;
       ArrayList<CustomProgressPojo> customProgressPojos = new ArrayList<>(smallTaskAndCustomProgressArrayList.size());
       for (SmallTaskAndCustomProgress smallTaskAndCustomProgress : smallTaskAndCustomProgressArrayList) {
           customProgressPojo = new CustomProgressPojo();
           customProgressPojo.setProgressOrder(smallTaskAndCustomProgress.getProgressorder());
           customProgressPojo.setCustomProgress(customProgressMapper.selectByPrimaryKey(smallTaskAndCustomProgress.getCustomprogressid()));
           customProgressPojos.add(customProgressPojo);
       }
       return customProgressPojos;
   }

    /**
     * 获取小任务进度
     * @param smallTaskID 小任务ID
     * @return 进度Double/NULL
     */
   public Integer getProgressBySmallTaskID(Integer smallTaskID) {
       SmallTaskAndNumberProgress smallTaskAndNumberProgress = smallTaskAndNumberProgressMapper.selectBySmallTaskID(smallTaskID);
       if (smallTaskAndNumberProgress != null) {
           Integer numberProgress = smallTaskAndNumberProgress.getNumberprogress();
           Integer finishedNumber = smallTaskAndNumberProgress.getFinishednumber();
           System.out.println("finishedprogress:" + finishedNumber + ", numberProgress:" + numberProgress);
           double progress = 1.0 * finishedNumber / numberProgress * 100;
           return (int)progress;
       } else {
           ArrayList<SmallTaskAndCustomProgress> smallTaskAndCustomProgressArrayList = smallTaskAndCustomProgressMapper.getSmallTaskAndCustomProgressBySmallTaskID(smallTaskID);
           if (smallTaskAndCustomProgressArrayList != null) {
               Integer progressSize = smallTaskAndCustomProgressArrayList.size();
               Integer haveFinishedNum = 0;
               CustomProgress customProgress = null;
               for (SmallTaskAndCustomProgress smallTaskAndCustomProgress : smallTaskAndCustomProgressArrayList) {
                    Integer customProgressID = smallTaskAndCustomProgress.getCustomprogressid();
                    if (customProgressID != null) {
                        customProgress = customProgressMapper.selectByPrimaryKey(customProgressID);
                        if (customProgress != null) {
                            if ("T".equals(customProgress.getHavefinished())) {
                                haveFinishedNum++;
                            }
                        }
                    }
               }
               double progress = 1.0 * haveFinishedNum / progressSize * 100;
               return (int)progress;
           }
       }
       return null;
   }
}
