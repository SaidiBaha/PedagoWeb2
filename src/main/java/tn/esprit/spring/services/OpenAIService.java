package tn.esprit.spring.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Option;
import tn.esprit.spring.entity.ParagrapheScraped;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repositories.OptionRepository;
import tn.esprit.spring.repositories.ParagrapheScrapedRepository;
import tn.esprit.spring.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service

public class OpenAIService {
    @Value("${chatgpt.api-key}")
    private String apiKey;


   @Autowired
    private  RestTemplate restTemplate;
   @Autowired
   private ObjectMapper objectMapper ;




    /////////////////////////////////////



    public String generateResponse(String inputText) {
        String apiUrl = "https://api.openai.com/v1/engines/" + "text-davinci-003" + "/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Create a request body as a Java object and serialize it to JSON
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", inputText);
        requestBody.put("max_tokens", 300);
        requestBody.put("temperature", 0.0);
        requestBody.put("top_p", 1.0);

        // Serialize the request body to JSON
        String requestBodyJson = null;
        try {
            requestBodyJson = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            // Handle JSON serialization error
            e.printStackTrace();
            return null;
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBodyJson, headers);

        // Send a POST request to the GPT-3 API
        String jsonResponse = restTemplate.postForObject(apiUrl, entity, String.class);

        // Parse the JSON response to extract the generated text
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode choicesNode = jsonNode.get("choices");
            if (choicesNode != null && choicesNode.isArray() && choicesNode.size() > 0) {
                JsonNode textNode = choicesNode.get(0).get("text");
                if (textNode != null) {
                    return textNode.asText();
                }
            }
        } catch (IOException e) {
            // Handle JSON parsing error
            e.printStackTrace();
        }

        return null; // Return null if there was an issue with the response
    }










 /*   public void generateQuestionsFromParagraph(Long paragraphId) throws IOException {
        ParagrapheScraped paragraph = paragrapheScrapedRepository.findById(paragraphId).orElse(null);
      //  System.out.println("test");
        if (paragraph == null) {
            throw new IllegalArgumentException("Paragraphe not found");
        }

        String content = paragraph.getContenu();

        // Générer des questions à partir du contenu du paragraphe en utilisant l'API OpenAI
        List<String> questions = generateQuestions(content);

        for (String questionText : questions) {
            // Create a new question
            Question question = new Question();
            question.setEnonce(questionText);
            question.setCorrectAnswer(""); // You need to set the correct answer manually

            // Save the question in the database
            questionRepository.save(question);
           System.out.println(question);

            // Generate options for the question (you need to implement this method)
            List<Option> options = generateOptionsForQuestion(questionText);

            for (Option option : options) {
                option.setQuestion(question);
            }

            // Save the options in the database
            optionRepository.saveAll(options);
        }
    }*/

   /* private List<String> generateQuestions(String content) throws IOException {
        // Use OkHttp to send a request to the OpenAI API to generate questions
        String openaiApiUrl = "https://chat.openai.com/?model=text-davinci-002-render-sha"; // Replace with the correct API endpoint
        String requestBody = "{\"prompt\": \"" + content + "\", \"temperature\": " + temperature + ", \"max_tokens\": " + maxTokens + "}";
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(openaiApiUrl)
                .post(RequestBody.create(requestBody, mediaType))
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }

            String responseBody = response.body().string();

            // Parse the response and extract the generated questions
            // You will need to adapt this based on the actual response format from the OpenAI API
            List<String> questions = new ArrayList<>();
            // Parse the response and extract the generated questions
            // Adapt this code to the actual response structure from the OpenAI API
            questions.add(responseBody);

            return questions;
        }
    }*/

    private List<Option> generateOptionsForQuestion(String questionText) {
        // Implémentez la génération d'options pour une question donnée
        // Vous pouvez générer des options de manière aléatoire ou en fonction du contenu de la question
        // Dans cet exemple simplifié, nous générons des options aléatoires :
        List<Option> options = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Option option = new Option();
            option.setOption("Option " + i);
            options.add(option);
        }
        return options;
    }



}
