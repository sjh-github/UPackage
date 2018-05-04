package com.wwdlb.hongruan.web.receivetaskpersonal;

import com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal.UpLoadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传工作成果
 */
@Controller
public class UpLoadController {

    @Autowired
    private UpLoadServiceImpl upLoadServiceImpl;

    @PostMapping(value = "/web/upLoad/numberProgress/{numberProgressID}/{smallTaskID}")
    public String upLoadNumberProgress(@PathVariable Integer numberProgressID, @PathVariable Integer smallTaskID,
                                       @RequestParam Integer haveFinishedNum,
                                       @RequestParam MultipartFile file) {
        if (upLoadServiceImpl.upLoadNumberProgress(numberProgressID, haveFinishedNum, file)) {
            return "redirect:/web/receiveTaskPerson/smallTaskDetailPage?smallTaskID=" + smallTaskID + "&result=true";
        }
        return "redirect:/web/receiveTaskPerson/smallTaskDetailPage?smallTaskID=" + smallTaskID + "&result=false";
    }

    @PostMapping(value = "/web/upLoad/customProgress/{smallTaskID}")
    public String upLoadCustomProgress(@PathVariable Integer smallTaskID) {
        return "redirect:/web/receiveTaskPerson/smallTaskDetailPage?smallTaskID=" + smallTaskID + "&result=true";
    }
}
