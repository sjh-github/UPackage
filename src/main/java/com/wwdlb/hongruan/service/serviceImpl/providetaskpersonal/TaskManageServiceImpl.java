package com.wwdlb.hongruan.service.serviceImpl.providetaskpersonal;

import com.wwdlb.hongruan.mapper.SmallTaskMapper;
import com.wwdlb.hongruan.mapper.TaskAndSmallTaskMapper;
import com.wwdlb.hongruan.mapper.TaskMapper;
import com.wwdlb.hongruan.model.SmallTask;
import com.wwdlb.hongruan.model.Task;
import com.wwdlb.hongruan.model.TaskAndSmallTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 任务管理服务实例
 */
@Service
public class TaskManageServiceImpl {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
	private TaskAndSmallTaskMapper taskAndSmallTaskMapper;

    @Autowired
	private SmallTaskMapper smallTaskMapper;

    /**
     * 获取所有项目
     * @return 项目列表
     */
    public ArrayList<Task> getAllTask() {
        return taskMapper.getAllTask();
    }
	
	/**
	*  获取所有项目名称
	*  @return 项目名称列表/NULL
	*/
	public ArrayList<String> getAllTaskName() {
		ArrayList<Task> allTaskList = getAllTask();
		if(allTaskList == null) {
			return null;
		}
		ArrayList<String> allTaskName = new ArrayList(allTaskList.size());
		for(Task task : allTaskList) {
			allTaskName.add(task.getTaskname());
		}
		return allTaskName;
	}

	/**
	 * 删除任务
	 * @param taskID 任务ID
	 * @return true/false
	 */
	public boolean deleteTask(Integer taskID) {
		if (taskID == null || taskID < 1) {
			return false;
		}
		Task task = taskMapper.selectByPrimaryKey(taskID);
		if (task == null) {
			return false;
		}
		//删除任务
		taskMapper.deleteByPrimaryKey(taskID);

		ArrayList<Integer> smallTaskIDList = taskAndSmallTaskMapper.getSmallTaskIDByTaskID(taskID);
		if (smallTaskIDList != null) {
			for (Integer smallTaskID : smallTaskIDList) {
				SmallTask smallTask = smallTaskMapper.selectByPrimaryKey(smallTaskID);
				if (smallTask != null) {
					//删除小任务
					smallTaskMapper.deleteByPrimaryKey(smallTaskID);
				}
			}
		}

		//删除任务小任务关联
		taskAndSmallTaskMapper.deleteByTaskID(taskID);
		return true;
	}

}
