package com.zyx.OnlineQuestionnaire.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zyx.OnlineQuestionnaire.dao.QuestionnaireMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionnaireService {

    private QuestionnaireMapper questionnaireMapper;

    public QuestionnaireService(QuestionnaireMapper questionnaireMapper) {
        this.questionnaireMapper = questionnaireMapper;
    }

    public List<Map<String, Object>> getQuestionnaire(Map<String, Object> param) {
        return questionnaireMapper.getQuestionniare(param);
    }

    public String createQuestionnaire(Map<String, Object> param) {
        List<String> questionIdList = Arrays.asList(param.get("list").toString().split("\\s*,\\s*"));
        List<Map<String, Object>> questionList = questionnaireMapper.getQuestionList(questionIdList);

        List<Object> points = (List<Object>) param.get("points");
        int total = 0;
        for (int i = 0; i < questionList.size(); i++) {
            questionList.get(i).put("point", points.get(i));
            total += (Integer) points.get(i);
        }

        String json = JSON.toJSONString(questionList);
        param.put("json", json);
        param.put("total", total);

        int result = questionnaireMapper.createQuestionnaire(param);

        if (result > 0) {
            return "插入成功！";
        } else {
            return "插入失败！";
        }
    }

    public String deleteQuestionnaire(Map<String, Object> param) {
        int result = questionnaireMapper.deleteQuestionnaire(param);

        if (result > 0) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }

    public Map<String, Object> submitQuestionnaire(Map<String, Object> param) {
        List<Map<String, Object>> questionnaire = getQuestionnaire(param);

        String questionsStr = questionnaire.get(0).get("questions").toString();
        List<Map> questionList = JSONArray.parseArray(questionsStr, Map.class);
        List<Object> studentAnswer = (List<Object>) param.get("studentAnswer");

        int score = 0;
        List<Integer> correctAnswerList = new LinkedList<>();
        for (int i = 0; i < questionList.size(); i++) {
            Map<String, Object> question = questionList.get(i);
            question.put("studentAnswer", studentAnswer.get(i));

            if ((Integer)question.get("type") == 1) {
                if (question.get("answer").toString().equals(""))
                    continue;
                else {
                    if (question.get("answer").equals(studentAnswer.get(i).toString())) {
                        score += (Integer) question.get("point");
                        correctAnswerList.add(i);
                    }
                }
            } else if ((Integer) question.get("type") == 2 || (Integer) question.get("type") == 3) {
                if (question.get("answer").equals(studentAnswer.get(i).toString())) {
                    score += (Integer) question.get("point");
                    correctAnswerList.add(i);
                }
            }
        }

        param.put("score", score);
        param.put("correctAnswerList", correctAnswerList.toString());
        param.put("studentAnswer", param.get("studentAnswer").toString());
        int result = questionnaireMapper.submitQuestionnaire(param);

        Map<String, Object> rsp = new HashMap<>();
        rsp.put("questionList", questionList);
        rsp.put("correctAnswerList", correctAnswerList);
        rsp.put("score", score);

        return rsp;
    }
}
