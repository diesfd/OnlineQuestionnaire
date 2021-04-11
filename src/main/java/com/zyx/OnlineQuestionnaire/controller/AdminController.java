package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.model.Question;
import com.zyx.OnlineQuestionnaire.service.QuestionService;
import com.zyx.OnlineQuestionnaire.service.QuestionnaireService;
import com.zyx.OnlineQuestionnaire.service.ResultService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private ResultService resultService;


    @RequestMapping(value = "questionnaire/create")
    public String createQuestionnaire(@RequestParam("title") String title, @RequestParam("list") String list,
                                      @RequestParam("points") String points) {
        Map<String, Object> param = new HashMap<>();
        param.put("title", title);
        param.put("list", list);
        param.put("points", points);
        return questionnaireService.createQuestionnaire(param);
    }

    @RequestMapping(value = "/questionnaire/createPage")
    public String createPage() {
        return "createQuestionnaire";
    }

    @RequestMapping(value = "questionnaire/delete")
    public String deleteQuestionnaire(@RequestParam String id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return questionnaireService.deleteQuestionnaire(param);
    }

    @RequestMapping(value = "/addPage")
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

    @RequestMapping(value = "/modifyPage")
    public String modifyPage() {
        return "modifyQuestion";
    }

    @RequestMapping(value = "/update")
    public String updateQuestion(@RequestBody Question param) {
        return questionService.updateQuestion(param);
    }

    @RequestMapping(value = "/search")
    public String searhQuestion(@RequestParam String type, @RequestParam String keyword, Model model) {
        Map<String, Object> param = new HashMap<>();
        param.put("type", type);
        if (keyword == null)
            param.put("keyword", "");
        else
            param.put("keyword", keyword);
        List<Map<String, Object>> result = questionService.searchQuestion(param);
        model.addAttribute("questionList", result);
        return "searchQuestion";
    }

    @RequestMapping(value = "/questionRepository")
    public String getAllQuestions(Model model) {
        List<Map<String, Object>> result = questionService.getAllQuestions();
        model.addAttribute("questionList", result);
        return "questionRepository";
    }

    @RequestMapping(value = "/resultRepository")
    public String getResultRepositoryPage(Model model) {
        List<Map<String, Object>> result = resultService.getAllResult();
        model.addAttribute("resultList", result);
        return "adminResultRepository";
    }

    @RequestMapping(value = "/markPage")
    public String getMarkPage(@RequestParam("id") String id, Model model) {
        Map<String, Object> result = resultService.getResultById(id);
        model.addAttribute("resultMap", result);
        return "markPage";
    }

    @RequestMapping(value = "/mark")
    public String mark(int id) {
        return resultService.mark(id);
    }
}
