package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @RequestMapping(value = "/create")
    public String createQuestionnaire(@RequestParam("title") String title, @RequestParam("list") String list,
                                      @RequestParam("points") String points) {
        Map<String, Object> param = new HashMap<>();
        param.put("title", title);
        param.put("list", list);
        param.put("points", points);
        return questionnaireService.createQuestionnaire(param);
    }

    @RequestMapping(value = "questionnaireRepository")
    public String repoPage() {
        return "questionnaireRepository";
    }

    @RequestMapping(value = "createPage")
    public String createPage() {
        return "createQuestionnaire";
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
