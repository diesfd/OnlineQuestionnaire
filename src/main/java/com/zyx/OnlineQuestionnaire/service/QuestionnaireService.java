package com.zyx.OnlineQuestionnaire.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zyx.OnlineQuestionnaire.dao.QuestionnaireMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionnaireService {

    private QuestionnaireMapper questionnaireMapper;

    public QuestionnaireService(QuestionnaireMapper questionnaireMapper) {
        this.questionnaireMapper = questionnaireMapper;
    }

    public List<Map<String, Object>> getAllQuestionnaire() {
        return questionnaireMapper.getAllQuestionniare();
    }

    public String createQuestionnaire(Map<String, Object> param) {
        List<String> questionIdList = Arrays.asList(param.get("list").toString().split("\\s*,\\s*"));
        List<Map<String, Object>> questionList = questionnaireMapper.getQuestionList(questionIdList);

        List<Object> points = Arrays.asList(param.get("points").toString().split("\\s*,\\s*"));
        int total = 0;
        for (int i = 0; i < questionList.size(); i++) {
            questionList.get(i).put("point", points.get(i));
            total += Integer.parseInt(points.get(i).toString());
        }

        String json = JSON.toJSONString(questionList);
        param.put("json", json);
        param.put("total", total);

        int result = questionnaireMapper.createQuestionnaire(param);

        if (result > 0) {
            return "createQuestionnaireSuccess";
        } else {
            return "createQuestionnaireFailed";
        }
    }

    public String deleteQuestionnaire(Map<String, Object> param) {
        int result = questionnaireMapper.deleteQuestionnaire(param);

        if (result > 0) {
            return "deleteQuestionnaireSuccess";
        } else {
            return "deleteQuestionnaireFailed";
        }
    }

    public Map<String, Object> submitQuestionnaire(Map<String, Object> param) {
        List<Map> questionList = getQuestionnaireById((Integer) param.get("questionnaireId"));

        List<String> studentAnswer = Arrays.asList(param.get("studentAnswer").toString().split(","));

        int doesntHaveBlankQuestion = 1;
        int score = 0;
//        List<String> correctAnswerList = new LinkedList<>();
        for (int i = 0; i < questionList.size(); i++) {
            Map<String, Object> question = questionList.get(i);
            question.put("studentAnswer", studentAnswer.get(i));

            if ((Integer)question.get("type") == 1) {
                question.put("isCorrect", "");
                doesntHaveBlankQuestion = 0;
            } else if ((Integer) question.get("type") == 2 || (Integer) question.get("type") == 3) {
                if ((Integer) question.get("type") == 3) {
                    question.put("studentAnswer", studentAnswer.get(i).equals("对"));
                }
                if (question.get("answer").equals(studentAnswer.get(i))) {
                    score += (Integer) question.get("point");
                    question.put("isCorrect","正确");
                } else {
                    question.put("isCorrect","错误");
                }
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentId = authentication.getName();

        param.put("studentId", studentId);
        param.put("marked", doesntHaveBlankQuestion);
        param.put("score", score);
//        param.put("correctAnswerList", correctAnswerList.toString());
        param.put("studentAnswer", param.get("studentAnswer").toString());
        param.put("questionnaireTitle", questionnaireMapper.getQuestionnaireTitle(param.get("questionnaireId").toString()));


        String questionListStr = JSON.toJSONString(questionList);
        param.put("questionList", questionListStr);
        questionnaireMapper.submitQuestionnaire(param);

        Map<String, Object> rsp = new HashMap<>();
        rsp.put("questionList", questionList);
        rsp.put("score", score);

        return rsp;
    }

    public List<Map> getQuestionnaireById(int questionnaireId) {
        Map<String, Object> param = new HashMap<>();
        param.put("questionnaireId", questionnaireId);
        List<Map<String, Object>> questionnaire = questionnaireMapper.getQuestionniare(param);
        String questionsStr = questionnaire.get(0).get("questions").toString();
        List<Map> questionList = JSONArray.parseArray(questionsStr, Map.class);

        for (Map<String, Object> question : questionList) {
            switch ((Integer) question.get("type")) {
                case 1:
                    question.put("typeName", "填空题");
                    break;
                case 2:
                    question.put("typeName", "选择题");
                    break;
                case 3:
                    question.put("typeName", "判断题");
                    break;
            }
        }

        return questionList;
    }


}
