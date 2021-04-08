package com.zyx.OnlineQuestionnaire.service;

import com.zyx.OnlineQuestionnaire.dao.QuestionMapper;
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
        if (param.get("type") == null || param.get("question") == null) {
            return "参数错误";
        }

        int result = 0;
        // 填空
        if ((Integer) param.get("type") == 1) { //填空
            param.put("answer", "");
            param.put("options", "");
            result = questionMapper.insertQuestion(param);
        } else if ((Integer) param.get("type") == 2) { //选择
            if (param.get("answer") == null ||param.get("answer").toString().length() == 0) {
                return "参数错误，没有答案";
            } else if (param.get("options") == null ||param.get("options").toString().length() == 0) {
                return "参数错误，没有选项";
            }
            result = questionMapper.insertQuestion(param);
        } else if ((Integer) param.get("type") == 3) { // 判断
            if (param.get("answer") == null ||param.get("answer").toString().length() == 0) {
                return "参数错误，没有答案";
            }
            param.put("options", "");
            result = questionMapper.insertQuestion(param);
        }

        if (result > 0) {
            return "插入成功！";
        } else {
            return "插入失败！";
        }
    }

    public String deleteQuestion(Map<String, Object> param) {
        if (param.get("id") == null || (Integer) param.get("id") < 0) {
            return "参数错误";
        }
        int result = questionMapper.deleteQuestion(param);
        if (result > 0) {
            return "删除成功！";
        } else {
            return "删除失败！";
        }
    }


    public String updateQuestion(Map<String, Object> param) {
        if (param.get("id") == null || param.get("type") == null || param.get("question") == null
        || param.get("options") == null || param.get("answer") == null) {
            return "参数错误";
        }
        int result = questionMapper.updateQuestion(param);
        if (result > 0) {
            return "修改成功！";
        } else {
            return "修改失败！";
        }
    }

    public List<Map<String, Object>> searchQuestion(Map<String, Object> param) {
        return questionMapper.searchQuestion(param);
    }
}
