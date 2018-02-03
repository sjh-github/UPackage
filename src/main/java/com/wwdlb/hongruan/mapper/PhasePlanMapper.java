package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.PhasePlan;

public interface PhasePlanMapper {
    int deleteByPrimaryKey(Integer phaseplanid);

    int insert(PhasePlan record);

    int insertSelective(PhasePlan record);

    PhasePlan selectByPrimaryKey(Integer phaseplanid);

    int updateByPrimaryKeySelective(PhasePlan record);

    int updateByPrimaryKey(PhasePlan record);
}