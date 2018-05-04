package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.ReceiveTask_Personal;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ReceiveTask_PersonalMapper {
    int deleteByPrimaryKey(String email);

    int insert(ReceiveTask_Personal record);

    int insertSelective(ReceiveTask_Personal record);

    ReceiveTask_Personal selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(ReceiveTask_Personal record);

    int updateByPrimaryKey(ReceiveTask_Personal record);

    List<ReceiveTask_Personal> selectAll();

    ArrayList<ReceiveTask_Personal> selectByHaveChecked(String haveChecked);

    int getNumOfAll();

    ArrayList<ReceiveTask_Personal> searchByNameAndHaveChecked(@Param("haveChecked") String haveChecked, @Param("name") String name);
}