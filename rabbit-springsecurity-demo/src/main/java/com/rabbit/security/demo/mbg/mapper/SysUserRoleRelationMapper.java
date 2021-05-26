package com.rabbit.security.demo.mbg.mapper;

import com.rabbit.security.demo.mbg.model.SysUserRoleRelation;
import com.rabbit.security.demo.mbg.model.SysUserRoleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleRelationMapper {
    int countByExample(SysUserRoleRelationExample example);

    int deleteByExample(SysUserRoleRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRoleRelation record);

    int insertSelective(SysUserRoleRelation record);

    List<SysUserRoleRelation> selectByExample(SysUserRoleRelationExample example);

    SysUserRoleRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserRoleRelation record, @Param("example") SysUserRoleRelationExample example);

    int updateByExample(@Param("record") SysUserRoleRelation record, @Param("example") SysUserRoleRelationExample example);

    int updateByPrimaryKeySelective(SysUserRoleRelation record);

    int updateByPrimaryKey(SysUserRoleRelation record);
}