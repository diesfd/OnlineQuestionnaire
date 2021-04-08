package com.zyx.OnlineQuestionnaire.model;

public class User {
    String studentId, name, password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
    }
}
