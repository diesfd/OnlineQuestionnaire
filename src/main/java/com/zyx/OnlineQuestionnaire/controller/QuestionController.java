package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/getAll")
    public List<Map<String, Object>> getAllQuestions() {
        List<Map<String, Object>> result = questionService.getAllQuestions();
        return result;
    }

    @RequestMapping(value = "/insert")
    public String insertQuestion(@RequestBody Map<String, Object> param) {
        return questionService.insertQuestion(param);
    }

    @RequestMapping(value = "/delete")
    public String deleteQuestion(@RequestBody Map<String, Object> param) {
        return questionService.deleteQuestion(param);
    }

    @RequestMapping(value = "/update")
    public String updateQuestion(@RequestBody Map<String, Object> param) {
        return questionService.updateQuestion(param);
    }

    @RequestMapping(value = "/search")
    public List<Map<String, Object>> searhQuestion(@RequestBody Map<String, Object> param) {
        return questionService.searchQuestion(param);
    }
}
