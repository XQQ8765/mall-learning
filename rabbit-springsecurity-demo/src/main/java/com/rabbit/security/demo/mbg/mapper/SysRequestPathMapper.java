package com.rabbit.security.demo.mbg.mapper;

import com.rabbit.security.demo.mbg.model.SysRequestPath;
import com.rabbit.security.demo.mbg.model.SysRequestPathExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRequestPathMapper {
    int countByExample(SysRequestPathExample example);

    int deleteByExample(SysRequestPathExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRequestPath record);

    int insertSelective(SysRequestPath record);

    List<SysRequestPath> selectByExample(SysRequestPathExample example);

    SysRequestPath selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRequestPath record, @Param("example") SysRequestPathExample example);

    int updateByExample(@Param("record") SysRequestPath record, @Param("example") SysRequestPathExample example);

    int updateByPrimaryKeySelective(SysRequestPath record);

    int updateByPrimaryKey(SysRequestPath record);
}