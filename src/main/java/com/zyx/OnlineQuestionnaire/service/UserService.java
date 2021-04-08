package com.zyx.OnlineQuestionnaire.service;

import com.zyx.OnlineQuestionnaire.dao.UserMapper;
import com.zyx.OnlineQuestionnaire.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public String addNewUser(User user) {
        List<User> users = findUserByStudentId(user);
        if (users != null && users.size() > 0) {
            return "repeatedAccount";
        }
        int result = userMapper.addNewUser(user);
        if (result >= 1)
            return "registerSuccess";
        else
            return "registerFailed";
    }

    public List<User> findUserByStudentId(User user) {
        return userMapper.findUserByStudentId(user);
    }

    public String login(User param) {
        List<User> users = findUserByStudentId(param);
        if (users == null || users.size() == 0) {
            return "该用户不存在";
        } else {
            User user = users.get(0);
            if (param.getPassword().equals(user.getPassword())) {
                // 如果密码与邮箱配对成功:
                return "登陆成功";
            } else {
                // 如果密码与邮箱不匹配:
                return "登录失败";
            }
        }
    }
}
