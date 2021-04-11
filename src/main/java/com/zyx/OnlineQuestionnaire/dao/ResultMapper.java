package com.zyx.OnlineQuestionnaire.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResultMapper {

    @Select("select * from t_result where student_id = #{studentId}")
    List<Map<String, Object>> getUserResult(String studentId);

    @Select("select * from t_result")
    List<Map<String, Object>> getAllResult();

    @Select("select * from t_result where id=#{id}")
    Map<String, Object> getResultById(String id);
}
