package com.zyx.OnlineQuestionnaire.model;

public class Question {
    int type, id;
    String question, answer, options;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Question(int type, int id, String question, String answer, String options) {
        this.type = type;
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.options = options;
    }
}
