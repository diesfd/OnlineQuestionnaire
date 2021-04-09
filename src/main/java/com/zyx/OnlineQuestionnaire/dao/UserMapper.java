package com.zyx.OnlineQuestionnaire.dao;

import com.zyx.OnlineQuestionnaire.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user (student_id, name, password) values (#{studentId}, #{name}, #{password})")
    int addNewUser(User user);

    @Select("select * from t_user tu where tu.student_id = #{studentId}")
    Optional<User> findUserByStudentId(int studentId);
}
