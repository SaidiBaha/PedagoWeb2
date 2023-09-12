package tn.esprit.spring.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerResponse {


    private String status;
    private String message;

    public AnswerResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
