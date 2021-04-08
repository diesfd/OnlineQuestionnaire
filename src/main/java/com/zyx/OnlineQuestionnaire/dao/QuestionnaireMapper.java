package com.zyx.OnlineQuestionnaire.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionnaireMapper {

    @Select({
            "<script>", "select", " * ", "FROM t_question",
            "WHERE  id IN  " +
                    "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'> #{item} </foreach>" +
                    "</script>"  })
    @Results({ })
    List<Map<String, Object>> getQuestionList(List<String> list);

    @Insert("insert into t_questionnaire (title, questions, total) values (#{title}, #{json}, #{total})")
    int createQuestionnaire(Map<String, Object> param);

    @Delete("delete from t_questionnaire where id = #{id}")
    int deleteQuestionnaire(Map<String, Object> param);

    @Select("<script>"+
            "select * from t_questionnaire tq"+
            "<where>"+
            " 1=1"+
            "<if test='questionnaireId != null'> and tq.id=#{questionnaireId}</if>"+
            "<if test='keyword != null'> and tq.title like concat('%',concat(#{keyword}, '%'))</if>"+
            "</where>"+
            "</script>")
    List<Map<String, Object>> getQuestionniare(Map<String, Object> param);

    @Select("select * from t_result tr where tr.student_id = #{studentId} and tr.questionnaire_id = #{questionnaireId}")
    List<Map<String, Object>> selectResult(Map<String, Object> param);

    @Insert("insert into t_result (student_id, student_answer, questionnaire_id, score, correct_answer_list) values (#{studentId}, #{studentAnswer}, #{questionnaireId}, #{score}, #{correctAnswerList})")
    int submitQuestionnaire(Map<String, Object> param);
}
