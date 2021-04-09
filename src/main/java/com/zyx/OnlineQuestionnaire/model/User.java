package com.zyx.OnlineQuestionnaire.model;

public class User {
    String student_id, name, password, role;

    public User(String student_id, String name, String password, String role) {
        this.student_id = student_id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
