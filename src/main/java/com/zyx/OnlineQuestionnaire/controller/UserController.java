package com.zyx.OnlineQuestionnaire.controller;

import com.zyx.OnlineQuestionnaire.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @RequestMapping(value="/add") // Map ONLY GET REQUESTs.
    public String addNewUser (@RequestBody Map<String, Object> param) {
        return userService.addNewUser(param);
    }

    @RequestMapping(value = "/login")
    public String login(@RequestBody Map<String, Object> param) {
        return userService.login(param);
    }

    /**
     * 域名的根目录，然后返回的“index”会映射到
     * java/resources/templates/index.html文件。
     *
     */
    @RequestMapping(path="/")
    public String welcomePage(@RequestParam(name="name", required=false, defaultValue="World") String namel){
        return "index";
    }
}