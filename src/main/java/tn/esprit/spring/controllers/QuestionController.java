package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Competence;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.services.OpenAIService;
import tn.esprit.spring.services.QuestionServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionServiceImp questionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/{sousCompetenceId}")
    public Question createQuestion(@PathVariable Long sousCompetenceId, @RequestBody Question question) {
        return questionService.createQuestion(sousCompetenceId, question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }


    @GetMapping("/bySousCompetence/{sousCompetenceId}")
    public ResponseEntity<List<Question>> getQuestionsBySousCompetenceId(@PathVariable Long sousCompetenceId) {
        List<Question> questions = questionService.getQuestionBySousCompetenceId(sousCompetenceId);
        return ResponseEntity.ok(questions);
    }


    @GetMapping("/by-SousCompetence/{sousCompetenceId}/question/{questionId}")
    public ResponseEntity<Question> getQuestionBySousCompetenceAndQuestionId(
            @PathVariable Long sousCompetenceId, @PathVariable Long questionId) {
        Question question = questionService.getQuestionBySousCompetenceAndQuestionId(sousCompetenceId, questionId);
        return ResponseEntity.ok(question);
    }


    // api Open AI
    private final OpenAIService questionGenerationService;

    @Autowired
    public QuestionController(OpenAIService questionGenerationService) {
        this.questionGenerationService = questionGenerationService;
    }


    //API OPenAI
  /*  @PostMapping("/generate")
    public ResponseEntity<String> generateQuestionsFromParagraph(@RequestParam Long paragraphId) {
        try {
            questionGenerationService.generateQuestionsFromParagraph(paragraphId);
            return ResponseEntity.ok("Questions generated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating questions: " + e.getMessage());
        }
    }*/

    @PostMapping("/ask-gpt")
    public String askGpt(@RequestBody String inputText) {
        return questionGenerationService.generateResponse(inputText);
    }




}
