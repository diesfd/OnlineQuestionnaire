package com.zyx.OnlineQuestionnaire.service;

import com.zyx.OnlineQuestionnaire.dao.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminService {

    private AdminMapper adminMapper;

    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public String login(Map<String, Object> param) {
        return "";
    }
}
