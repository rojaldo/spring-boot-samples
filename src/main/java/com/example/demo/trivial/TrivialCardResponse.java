package com.example.demo.trivial;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TrivialCardResponse {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    TrivialCardResponse(){

    }
    public TrivialEntity convertToEntity(){
        TrivialEntity entity = new TrivialEntity();
        entity.setCategory(category);
        entity.setCorrect_answer(correct_answer);
        entity.setDifficulty(difficulty);
        entity.setQuestion(question);
        entity.setType(type);
        entity.setIncorrect_answers(incorrect_answers.stream().collect(Collectors.joining(";")));
        return entity;
    }

    public String getCategory() {
        return category;
    }
    public String getCorrect_answer() {
        return correct_answer;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }
    public String getQuestion() {
        return question;
    }
    public String getType() {
        return type;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public void setIncorrect_answers(List<String> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}
