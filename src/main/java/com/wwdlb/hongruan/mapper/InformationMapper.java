package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.Information;

import java.util.ArrayList;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer inforid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer inforid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    ArrayList<Information> selectByToEmail(String toEmail);
}