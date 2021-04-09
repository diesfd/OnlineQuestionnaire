package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.model.User;
import com.zyx.OnlineQuestionnaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "")
    public String homePage() {
        return "index";
    }

    @RequestMapping(value = "register")
    public String registerPage() { return "addUser"; }

    @RequestMapping(value = "/addUser")
    public String register(@RequestParam("name") String name, @RequestParam("studentId") String studentId,
                           @RequestParam("password") String password) {
        User user = new User(studentId, name, password);
        return userService.addNewUser(user);
    }
}
