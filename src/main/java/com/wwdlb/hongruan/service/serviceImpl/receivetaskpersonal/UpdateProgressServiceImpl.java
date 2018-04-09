package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.mapper.CustomProgressMapper;
import com.wwdlb.hongruan.mapper.SmallTaskAndCustomProgressMapper;
import com.wwdlb.hongruan.mapper.SmallTaskAndNumberProgressMapper;
import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.model.CustomProgress;
import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.model.SmallTaskAndCustomProgress;
import com.wwdlb.hongruan.model.SmallTaskAndNumberProgress;
import com.wwdlb.hongruan.pojo.CustomProgressIDAndResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 更新小任务进度服务实例
 */
@Service
@Transactional
public class UpdateProgressServiceImpl {
    @Autowired
    private SmallTaskAndNumberProgressMapper smallTaskAndNumberProgressMapper;

    @Autowired
    private CustomProgressMapper customProgressMapper;

    @Autowired
    private SmallTaskMapper smallTaskMapper;

    /**
     * 更新任务进度
     * @param smallTaskID 小任务ID
     * @param newNumberProgress 完成数量
     * @return true/false
     */
    public boolean updateNumberProgress(Integer smallTaskID, Integer newNumberProgress) {
        SmallTaskAndNumberProgress smallTaskAndNumberProgress = smallTaskAndNumberProgressMapper.selectBySmallTaskID(smallTaskID);
        if (smallTaskAndNumberProgress == null) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        smallTaskAndNumberProgress.setFinishednumber(newNumberProgress);
        //完成指标
        if (newNumberProgress >= smallTaskAndNumberProgress.getNumberprogress()) {
            SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
            if (smallTask != null) {
                smallTask.setHavefinished("T");
                smallTask.setFinishtime(simpleDateFormat.format(date));
                smallTaskMapper.updateByPrimaryKeySelective(smallTask);
            }
        }
        smallTaskAndNumberProgress.setUpdatetime(simpleDateFormat.format(date));
        smallTaskAndNumberProgressMapper.updateByPrimaryKeySelective(smallTaskAndNumberProgress);
        return true;
    }

    /**
     * 更新自定义指标完成情况
     * @param customProgressIDAndResults 自定义指标ID及完成结果（boolean）
     * @return true/false
     */
    public boolean updateCustomProgress(ArrayList<CustomProgressIDAndResult> customProgressIDAndResults) {
        if (customProgressIDAndResults == null || customProgressIDAndResults.size() < 1) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        for (CustomProgressIDAndResult customProgressIDAndResult : customProgressIDAndResults) {
            //该指标已完成
            if (customProgressIDAndResult.isHaveFinihed()) {
                //获取自定义指标
                CustomProgress customProgress = customProgressMapper.selectByPrimaryKey(customProgressIDAndResult.getCustomProgressID());
                if (customProgress != null) {
                    if (customProgress.getHavefinished().equals("F")) {
                        customProgress.setHavefinished("T");
                        customProgress.setFinishtime(simpleDateFormat.format(date));
                        customProgressMapper.updateByPrimaryKeySelective(customProgress);
                        System.out.println(customProgress.getCustomprogressid());
                    }
                }
            }
        }
        return true;
    }
}
