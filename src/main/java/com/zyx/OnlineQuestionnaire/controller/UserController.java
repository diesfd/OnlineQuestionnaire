package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.service.QuestionService;
import com.zyx.OnlineQuestionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionnaireService questionnaireService;


    @RequestMapping(value = "/questionnaireRepository")
    public String getAllQuestionnaire(Model model) {
        List<Map<String, Object>> result = questionnaireService.getAllQuestionnaire();
        model.addAttribute("questionnaireList", result);
        return "questionnaireRepository";
    }

    @RequestMapping(value = "/submit")
    public Map<String, Object> submitQuestionnaire(@RequestBody Map<String, Object> param) {
        return questionnaireService.submitQuestionnaire(param);
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