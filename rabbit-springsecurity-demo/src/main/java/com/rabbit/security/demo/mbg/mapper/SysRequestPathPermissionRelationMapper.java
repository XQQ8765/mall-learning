package com.rabbit.security.demo.mbg.mapper;

import com.rabbit.security.demo.mbg.model.SysRequestPathPermissionRelation;
import com.rabbit.security.demo.mbg.model.SysRequestPathPermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRequestPathPermissionRelationMapper {
    int countByExample(SysRequestPathPermissionRelationExample example);

    int deleteByExample(SysRequestPathPermissionRelationExample example);

    int insert(SysRequestPathPermissionRelation record);

    int insertSelective(SysRequestPathPermissionRelation record);

    List<SysRequestPathPermissionRelation> selectByExample(SysRequestPathPermissionRelationExample example);

    int updateByExampleSelective(@Param("record") SysRequestPathPermissionRelation record, @Param("example") SysRequestPathPermissionRelationExample example);

    int updateByExample(@Param("record") SysRequestPathPermissionRelation record, @Param("example") SysRequestPathPermissionRelationExample example);
}