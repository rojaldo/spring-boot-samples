package com.example.demo.trivial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TrivialRestController {
    @Autowired
    private TrivialRepository preguntaRepository;

    @GetMapping("/trivial")
    public ResponseEntity<TrivialListResponse> getAll() {
        List<TrivialCardResponse> response = new ArrayList<TrivialCardResponse>();
        TrivialListResponse result = new TrivialListResponse();
        preguntaRepository.findAll().forEach(e -> response.add(e.converToResponse()));
        result.setResponse_code(0);
        result.setResults(response);
        return new ResponseEntity<TrivialListResponse>(result, HttpStatus.OK);
    }

    @GetMapping("/trivial/{id}")
    public ResponseEntity<TrivialListResponse> getPub(@PathVariable Long id) {
        List<TrivialCardResponse> response = new ArrayList<TrivialCardResponse>();
        TrivialListResponse result = new TrivialListResponse();
        try {
            response.add(preguntaRepository.findById(id).get().converToResponse());
            result.setResponse_code(0);
            result.setResults(response);
            return new ResponseEntity<TrivialListResponse>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setResponse_code(-1);
            return new ResponseEntity<TrivialListResponse>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/trivial")
    public ResponseEntity<TrivialEntity> createPub(@RequestBody TrivialCardResponse pregunta) {
        try {
            if (!pregunta.getIncorrect_answers().stream().anyMatch(p -> p.contains(";"))) {
                return new ResponseEntity<TrivialEntity>(
                        preguntaRepository.save(pregunta.convertToEntity()), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<TrivialEntity>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<TrivialEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/trivial/{id}")
    public ResponseEntity<TrivialEntity> updatePub(@PathVariable Long id, @RequestBody TrivialCardResponse response) {
        try {
            if (!response.getIncorrect_answers().stream().anyMatch(p -> p.contains(";"))) {
                TrivialEntity entity = preguntaRepository.findById(id).get();
                TrivialEntity responseToSave = response.convertToEntity();
                entity.setCategory(responseToSave.getCategory());
                entity.setCorrect_answer(responseToSave.getCorrect_answer());
                entity.setDifficulty(responseToSave.getDifficulty());
                entity.setIncorrect_answers(responseToSave.getIncorrect_answers());
                entity.setQuestion(responseToSave.getQuestion());
                entity.setType(responseToSave.getType());
                return new ResponseEntity<TrivialEntity>(preguntaRepository.save(entity),
                        HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<TrivialEntity>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<TrivialEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/trivial/{id}")
    public ResponseEntity<TrivialCardResponse> deletePub(@PathVariable Long id) {
        TrivialCardResponse pregunta = new TrivialCardResponse();
        try {
            preguntaRepository.deleteById(id);
            return new ResponseEntity<TrivialCardResponse>(pregunta, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<TrivialCardResponse>(pregunta, HttpStatus.NOT_FOUND);
        }
    }
}
