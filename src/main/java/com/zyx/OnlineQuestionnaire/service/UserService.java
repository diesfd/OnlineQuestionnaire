package com.zyx.OnlineQuestionnaire.service;

import com.zyx.OnlineQuestionnaire.dao.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserMapper userMapper;

//    public UserService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

//    public String addNewUser(User user) {
//        Optional<User> users = findUserByStudentId(user);
//        if (users != null && users.size() > 0) {
//            return "createUserFailed";
//        }
//        int result = userMapper.addNewUser(user);
//        if (result >= 1)
//            return "createUserSuccess";
//        else
//            return "createUserFailed";
//    }

//    public Optional<User> findUserByStudentId(User user) {
//        return userMapper.findUserByStudentId(user.getStudentId());
//    }

//    public String login(User param) {
//        Optional<User> users = findUserByStudentId(param);
//        if (users == null || users.size() == 0) {
//            return "该用户不存在";
//        } else {
//            User user = users.get(0);
//            if (param.getPassword().equals(user.getPassword())) {
//                // 如果密码与邮箱配对成功:
//                return "登陆成功";
//            } else {
//                // 如果密码与邮箱不匹配:
//                return "登录失败";
//            }
//        }
//    }
}
