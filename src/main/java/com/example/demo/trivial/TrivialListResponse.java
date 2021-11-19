package com.example.demo.trivial;

import java.util.List;

public class TrivialListResponse {
    private int response_code;
    private List<TrivialCardResponse> results;
    public int getResponse_code() {
        return response_code;
    }
    public List<TrivialCardResponse> getResults() {
        return results;
    }
    public TrivialListResponse(){
        
    }
    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }
    public void setResults(List<TrivialCardResponse> results) {
        this.results = results;
    }
}
