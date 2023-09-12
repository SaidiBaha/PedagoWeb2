package tn.esprit.spring.entity;


import lombok.Data;

@Data

public class ScrapedDataResponse {

    private String scrapedData;

    public ScrapedDataResponse(String scrapedData) {
        this.scrapedData = scrapedData;
    }
}
