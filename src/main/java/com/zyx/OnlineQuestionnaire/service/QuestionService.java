package com.zyx.OnlineQuestionnaire.service;

import com.zyx.OnlineQuestionnaire.dao.QuestionMapper;
import com.zyx.OnlineQuestionnaire.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    private QuestionMapper questionMapper;

    public QuestionService(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public List<Map<String, Object>> getAllQuestions(){
        return questionMapper.getAllQuestions();
    }

    public String insertQuestion(Map<String, Object> param) {

        int result = 0;
        // 填空
        if ((Integer)param.get("type") == 1) { //填空
            param.put("answer", "");
            param.put("options", "");
            result = questionMapper.insertQuestion(param);
        } else if ((Integer)param.get("type") == 2) { //选择
            if (param.get("answer") == null ||param.get("answer").toString().length() == 0) {
                return "wrong parameter";
            } else if (param.get("options") == null ||param.get("options").toString().length() == 0) {
                return "wrong parameter";
            }
            result = questionMapper.insertQuestion(param);
        } else if ((Integer)param.get("type") == 3) { // 判断
            if (param.get("answer") == null ||param.get("answer").toString().length() == 0) {
                return "wrong parameter";
            }
            param.put("options","");
            result = questionMapper.insertQuestion(param);
        }

        if (result > 0) {
            return "insertQuestionSuccess";
        } else {
            return "insertQuestionFailed";
        }
    }

    public String deleteQuestion(Question question) {
        int result = questionMapper.deleteQuestion(question);
        if (result > 0) {
            return "deleteQuestionSuccess";
        } else {
            return "deleteQuestionFailed";
        }
    }


    public String updateQuestion(Question question) {
        int result = questionMapper.updateQuestion(question);
        if (result > 0) {
            return "modifyQuestionSuccess";
        } else {
            return "modifyQuestionFailed";
        }
    }

    public List<Map<String, Object>> searchQuestion(Map<String, Object> param) {
        return questionMapper.searchQuestion(param);
    }
}
