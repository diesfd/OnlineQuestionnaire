package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.model.Question;
import com.zyx.OnlineQuestionnaire.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/questionRepository")
    public String getAllQuestions(Model model) {
        List<Map<String, Object>> result = questionService.getAllQuestions();
        model.addAttribute("questionList", result);
        return "questionRepository";
    }

    @RequestMapping(value = "/add")
    public String addPage() {
        return "newQuestion";
    }

    @RequestMapping(value = "/insert")
    public String insertQuestion(@RequestParam("type") int type, @RequestParam("question") String question,
                                 @RequestParam("answer") String answer, @RequestParam("options") String options) {
        Map<String, Object> param = new HashMap<>();
        param.put("type", type);
        param.put("question", question);
        param.put("answer", answer);
        param.put("options", options);
        return questionService.insertQuestion(param);
    }

    @RequestMapping(value = "/delete")
    public String deleteQuestion(@RequestParam Integer id) {
        return questionService.deleteQuestion(id);
    }

    @RequestMapping(value = "/modify")
    public String modifyPage() {
        return "modifyQuestion";
    }

    @RequestMapping(value = "/update")
    public String updateQuestion(@RequestBody Question param) {
        return questionService.updateQuestion(param);
    }

    @RequestMapping(value = "/search")
    public String searhQuestion(@RequestBody Map<String, Object> param) {
        List<Map<String, Object>> result = questionService.searchQuestion(param);
        return "questionRepository";
    }
}
