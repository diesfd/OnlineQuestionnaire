package com.zyx.OnlineQuestionnaire.service;

import com.zyx.OnlineQuestionnaire.dao.UserMapper;
import com.zyx.OnlineQuestionnaire.model.MyUserDetails;
import com.zyx.OnlineQuestionnaire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String studentId) throws UsernameNotFoundException {
        Optional<User> user = userMapper.findUserByStudentId(Integer.parseInt(studentId));

        user.orElseThrow(() -> new UsernameNotFoundException("not found:" + studentId));

        return user.map(MyUserDetails::new).get();
    }
}
