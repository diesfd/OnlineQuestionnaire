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

    public String insertQuestion(Question question) {

        int result = 0;
        // 填空
        if (question.getType() == 1) { //填空
            question.setAnswer("");
            question.setOptions("");
            result = questionMapper.insertQuestion(question);
        } else if (question.getType() == 2) { //选择
            if (question.getAnswer() == null ||question.getAnswer().length() == 0) {
                return "wrong parameter";
            } else if (question.getOptions() == null ||question.getOptions().length() == 0) {
                return "wrong parameter";
            }
            result = questionMapper.insertQuestion(question);
        } else if (question.getType() == 3) { // 判断
            if (question.getAnswer() == null ||question.getAnswer().length() == 0) {
                return "wrong parameter";
            }
            question.setOptions("");
            result = questionMapper.insertQuestion(question);
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
