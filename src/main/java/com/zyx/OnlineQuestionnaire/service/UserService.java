package com.zyx.OnlineQuestionnaire.service;

import com.zyx.OnlineQuestionnaire.dao.UserMapper;

import java.util.List;
import java.util.Map;

public class UserService {

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String addNewUser(Map<String, Object> param) {
        List<Map<String, Object>> users = findUserByStudentId(param);
        if (users != null || users.size() > 0) {
            return "用户已存在";
        }
        int result = userMapper.addNewUser(param);
        if (result > 1)
            return "插入成功！";
        else
            return "插入失败";
    }

    public List<Map<String, Object>> findUserByStudentId(Map<String, Object> param) {
        return userMapper.findUserByStudentId(param);
    }

    public String login(Map<String, Object> param) {
        List<Map<String, Object>> users = findUserByStudentId(param);
        if (users == null || users.size() == 0) {
            return "该用户不存在";
        } else {
            Map user = users.get(0);
            if (user.get("password").equals(param.get("password"))) {
                // 如果密码与邮箱配对成功:
                return "登陆成功";
            } else {
                // 如果密码与邮箱不匹配:
                return "登录失败";
            }
        }
    }
}
