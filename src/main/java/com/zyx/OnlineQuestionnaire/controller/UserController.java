package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.model.User;
import com.zyx.OnlineQuestionnaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/add")
    public String addNewUser (@RequestParam("name") String name, @RequestParam String studentId,
                              @RequestParam String password) {
        User user = new User(studentId, name, password);
        return userService.addNewUser(user);
    }

    @RequestMapping(value = "/register")
    public String regiseter() {
        return "addUser";
    }


    @PostMapping(value = "/login")
    public String login(@RequestParam("name") String name, @RequestParam String studentId,
                        @RequestParam String password) {
        User user = new User(studentId, name, password);
        return userService.login(user);
    }

}