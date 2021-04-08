package com.zyx.OnlineQuestionnaire.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface AdminMapper {

    @Select("select ta.password from t_admin ta where ta.admin_id = #{adminId}")
    String getPassword(Map<String, Object> param);
}
