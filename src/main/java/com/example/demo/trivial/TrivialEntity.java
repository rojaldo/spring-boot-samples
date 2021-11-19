package com.example.demo.trivial;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrivialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String incorrect_answers;

    public TrivialEntity() {

    }

    public TrivialCardResponse converToResponse(){
        TrivialCardResponse response = new TrivialCardResponse();
        response.setCategory(category);
        response.setCorrect_answer(correct_answer);
        response.setDifficulty(difficulty);
        response.setIncorrect_answers(Arrays.asList(incorrect_answers.split(";")));
        response.setQuestion(question);
        response.setType(type);
        return response;
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

    public Long getId() {
        return id;
    }

    public String getIncorrect_answers() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setIncorrect_answers(String incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setType(String type) {
        this.type = type;
    }
}
