package tn.esprit.spring.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private ThematiqueServiceImp thematiqueService;
    private UserServiceImp userService ;

    @GetMapping("/Thematiques")
    public List<Thematique> getAllThematiques() {
        return thematiqueService.getAllThematiques();
    }

    @GetMapping("/Thematiques/{id}")
    public Thematique getThematiqueById(@PathVariable Long id) {
        return thematiqueService.getThematiqueById(id);
    }

    @Autowired
    private SousCompetenceServiceImp sousCompetenceService;

    @GetMapping("/SousCompetence")
    public List<SousCompetence> getAllSousCompetences() {
        return sousCompetenceService.getAllSousCompetences();
    }

    @GetMapping("/SousCompetence/{id}")
    public SousCompetence getSousCompetenceById(@PathVariable Long id) {
        return sousCompetenceService.getSousCompetenceById(id);
    }

    @Autowired
    private QuestionServiceImp questionService;

    @GetMapping("/Question")

    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/Question/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }


    private OptionServiceImp optionService;

    @GetMapping("/Option")
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping("/Option/{id}")
    public Option getOptionById(@PathVariable Long id) {
        return optionService.getOptionById(id);
    }

    private CompetenceServiceImp competenceService;

    @GetMapping("/Competence")
    public List<Competence> getAllCompetences() {
        return competenceService.getAllCompetences();
    }

    @GetMapping("/Competence/{id}")
    public Competence getCompetenceById(@PathVariable Long id) {
        return competenceService.getCompetenceById(id);
    }

//---------------------------------------------------------------------//

    @PostMapping("/{userId}/answer/{sousCompetenceId}/question/{questionId}")
    public ResponseEntity<AnswerResponse> answerQuestion(
            @PathVariable Long userId,
            @PathVariable Long sousCompetenceId,
            @PathVariable Long questionId,
            @RequestParam String answer
    ){
        String message = userService.answerQuestion(userId, sousCompetenceId, questionId, answer);
        String status = message.equals("ok") ? "success" : "failure";
        AnswerResponse response = new AnswerResponse(status, message);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/getUserScore")
    public ResponseEntity<Integer> getUserScore(@RequestParam Long userId) {
        Integer score = userService.getUserTotalScore(userId);
        return ResponseEntity.ok(score);
    }



}


