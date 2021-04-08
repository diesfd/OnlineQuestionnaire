package com.zyx.OnlineQuestionnaire.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user (student_id, name, password) values (#{studentId, name, password})")
    int addNewUser(Map<String, Object> param);

    @Select("select * from t_user tu where tu.student_id = #{studentId}")
    List<Map<String, Object>> findUserByStudentId(Map<String, Object> param);
}
