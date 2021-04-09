package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

//@Controller
//@ResponseBody
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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

}