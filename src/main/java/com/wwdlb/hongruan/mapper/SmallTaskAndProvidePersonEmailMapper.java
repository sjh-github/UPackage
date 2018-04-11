package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.SmallTaskAndProvidePersonEmail;

public interface SmallTaskAndProvidePersonEmailMapper {
    int deleteByPrimaryKey(String email);

    int insert(SmallTaskAndProvidePersonEmail record);

    int insertSelective(SmallTaskAndProvidePersonEmail record);

    SmallTaskAndProvidePersonEmail selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(SmallTaskAndProvidePersonEmail record);

    int updateByPrimaryKey(SmallTaskAndProvidePersonEmail record);
}