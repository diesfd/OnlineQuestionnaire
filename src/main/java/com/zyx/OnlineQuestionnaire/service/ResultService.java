package com.zyx.OnlineQuestionnaire.service;

import com.alibaba.fastjson.JSONArray;
import com.zyx.OnlineQuestionnaire.dao.QuestionnaireMapper;
import com.zyx.OnlineQuestionnaire.dao.ResultMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResultService {

    private QuestionnaireMapper questionnaireMapper;

    private ResultMapper resultMapper;


    public ResultService(QuestionnaireMapper questionnaireMapper, ResultMapper resultMapper) {
        this.questionnaireMapper = questionnaireMapper;
        this.resultMapper = resultMapper;
    }

    public List<Map<String, Object>> getUserResult() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String studentId = authentication.getName();

        return resultMapper.getUserResult(studentId);
    }

    public List<Map<String, Object>> getAllResult() {
        return resultMapper.getAllResult();
    }

    public Map<String, Object> getResultById(String id) {
        Map<String, Object> result = resultMapper.getResultById(id);
        String questionListStr = result.get("question_list").toString();
        List<Map> questionList = JSONArray.parseArray(questionListStr, Map.class);
        result.put("question_list", questionList);
        return result;
    }

    public String mark(int id) {
        return "";
    }

}
