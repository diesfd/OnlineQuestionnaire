package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @RequestMapping(value = "/create")
    public String createQuestionnaire(@RequestBody Map<String, Object> param) {
        return questionnaireService.createQuestionnaire(param);
    }

    @RequestMapping(value = "/delete")
    public String deleteQuestionnaire(@RequestBody Map<String, Object> param) {
        return questionnaireService.deleteQuestionnaire(param);
    }

    @RequestMapping(value = "/get")
    public List<Map<String, Object>> getQuestionnaire(@RequestBody Map<String, Object> param) {
        return questionnaireService.getQuestionnaire(param);
    }

    @RequestMapping(value = "/submit")
    public Map<String, Object> submitQuestionnaire(@RequestBody Map<String, Object> param) {
        return questionnaireService.submitQuestionnaire(param);
    }
}
