package com.wwdlb.hongruan.service.serviceImpl.receivetaskpersonal;

import com.wwdlb.hongruan.Info.Info;
import com.wwdlb.hongruan.mapper.SmallTaskAndNumberProgressMapper;
import com.wwdlb.hongruan.model.SmallTaskAndNumberProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UpLoadServiceImpl {
   @Autowired
    private SmallTaskAndNumberProgressMapper smallTaskAndNumberProgressMapper;

   public boolean upLoadNumberProgress(Integer numberProgressID, Integer haveFinishedNum, MultipartFile multipartFile) {
       SmallTaskAndNumberProgress smallTaskAndNumberProgress = smallTaskAndNumberProgressMapper.selectByPrimaryKey(numberProgressID);
       if (smallTaskAndNumberProgress != null) {
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           Date date = new Date();
           smallTaskAndNumberProgress.setUpdatetime(simpleDateFormat.format(date));
           smallTaskAndNumberProgress.setFinishednumber(haveFinishedNum);
           smallTaskAndNumberProgressMapper.updateByPrimaryKeySelective(smallTaskAndNumberProgress);
       } else {
           return false;
       }
       if (multipartFile != null && !multipartFile.isEmpty()) {
           new Thread(new Runnable() {
               @Override
               public void run() {
                   File file = new File(Info.upLoadAddress + numberProgressID + "_" + multipartFile.getOriginalFilename());
                   System.out.println(Info.upLoadAddress + numberProgressID + "_" + multipartFile.getOriginalFilename());
                   BufferedOutputStream bufferedOutputStream = null;
                   try {
                       bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   }
                   try {
                       bufferedOutputStream.write(multipartFile.getBytes());
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   try {
                       bufferedOutputStream.flush();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   try {
                       bufferedOutputStream.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }).start();
       }
       return true;
   }
}
