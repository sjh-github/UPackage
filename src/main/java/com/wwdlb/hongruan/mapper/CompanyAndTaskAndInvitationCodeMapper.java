package com.wwdlb.hongruan.mapper;

import com.wwdlb.hongruan.model.CompanyAndTaskAndInvitationCode;

public interface CompanyAndTaskAndInvitationCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompanyAndTaskAndInvitationCode record);

    int insertSelective(CompanyAndTaskAndInvitationCode record);

    CompanyAndTaskAndInvitationCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompanyAndTaskAndInvitationCode record);

    int updateByPrimaryKey(CompanyAndTaskAndInvitationCode record);
}