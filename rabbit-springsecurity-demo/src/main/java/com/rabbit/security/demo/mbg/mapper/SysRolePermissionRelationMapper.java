package com.rabbit.security.demo.mbg.mapper;

import com.rabbit.security.demo.mbg.model.SysRolePermissionRelation;
import com.rabbit.security.demo.mbg.model.SysRolePermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRolePermissionRelationMapper {
    int countByExample(SysRolePermissionRelationExample example);

    int deleteByExample(SysRolePermissionRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermissionRelation record);

    int insertSelective(SysRolePermissionRelation record);

    List<SysRolePermissionRelation> selectByExample(SysRolePermissionRelationExample example);

    SysRolePermissionRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRolePermissionRelation record, @Param("example") SysRolePermissionRelationExample example);

    int updateByExample(@Param("record") SysRolePermissionRelation record, @Param("example") SysRolePermissionRelationExample example);

    int updateByPrimaryKeySelective(SysRolePermissionRelation record);

    int updateByPrimaryKey(SysRolePermissionRelation record);
}