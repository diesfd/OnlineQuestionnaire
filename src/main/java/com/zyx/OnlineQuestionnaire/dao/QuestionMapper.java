package com.zyx.OnlineQuestionnaire.dao;

import com.zyx.OnlineQuestionnaire.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapper {

    @Select("select * from t_question")
    List<Map<String, Object>> getAllQuestions();

    @Insert("insert into t_question(type, question, answer, options) values (#{type}, #{question}, #{answer}, #{options})")
    int insertQuestion(Map<String, Object> param);

    @Delete("delete from t_question where id = #{id}")
    int deleteQuestion(Integer id);

    @Update("UPDATE t_question SET type = #{type}, question = #{question}, answer = #{answer}, options = #{options} WHERE id = #{id}")
    int updateQuestion(Question param);

    @Select("<script>"+
            "select * from t_question tq"+
            "<where>"+
            " 1=1"+
            "<if test='type != null and type != 0'> and tq.type=#{type}</if>"+
            "<if test='keyword != null'> and tq.question like concat('%',concat(#{keyword}, '%'))</if>"+
            "</where>"+
            "</script>")
    List<Map<String, Object>> searchQuestion(Map<String, Object> param);
}

