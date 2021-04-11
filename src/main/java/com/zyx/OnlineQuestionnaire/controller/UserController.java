package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.service.QuestionnaireService;
import com.zyx.OnlineQuestionnaire.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private ResultService resultService;


    @RequestMapping(value = "/questionnaireRepository")
    public String getAllQuestionnaire(Model model) {
        List<Map<String, Object>> result = questionnaireService.getAllQuestionnaire();
        model.addAttribute("questionnaireList", result);
        return "questionnaireRepository";
    }

    @RequestMapping(value = "/answerPage")
    public String answerPage(@RequestParam("questionnaireId") int questionnaireId, Model model) {
        List<Map> result = questionnaireService.getQuestionnaireById(questionnaireId);
        model.addAttribute("questionList", result);
        return "answerPage";
    }

    @RequestMapping(value = "/submit")
    public String submitQuestionnaire(@RequestParam("questionnaireId") int questionnaireId,
                                      @RequestParam("studentAnswer") String studentAnswer,
                                      Model model) {
        Map<String, Object> param = new HashMap<>();
        param.put("questionnaireId",questionnaireId);
        param.put("studentAnswer", studentAnswer);
        Map<String, Object> result = questionnaireService.submitQuestionnaire(param);
        model.addAttribute("resultMap", result);
        return "questionnaireResult";
    }

    @RequestMapping(value = "/resultRepository")
    public String resultRepositoryPage(Model model) {
        List<Map<String, Object>> result = resultService.getUserResult();
        model.addAttribute("resultList", result);
        return "userResultRepository";
    }
}
//    @PostMapping(value="/add")
//    public String addNewUser (@RequestParam("name") String name, @RequestParam String studentId,
//                              @RequestParam String password) {
//        User user = new User(studentId, name, password);
//        return userService.addNewUser(user);
//    }

//    @RequestMapping(value = "/register")
//    public String regiseter() {
//        return "addUser";
//    }


//    @PostMapping(value = "/login")
//    public String login(@RequestParam("name") String name, @RequestParam String studentId,
//                        @RequestParam String password) {
//        User user = new User(studentId, name, password);
//        return userService.login(user);
//    }