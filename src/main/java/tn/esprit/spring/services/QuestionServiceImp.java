package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.QuestionRepository;
import tn.esprit.spring.repositories.SousCompetenceRepository;


import java.util.List;
import java.util.Optional;


@AllArgsConstructor

@Service
public class QuestionServiceImp implements IQuestionService {

    private QuestionRepository questionRepository;
    private SousCompetenceRepository sousCompetenceRepository ;


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + id));
    }

    public Question createQuestion(Long sousCompetenceId, Question question) {
        SousCompetence sousCompetence = sousCompetenceRepository.findById(sousCompetenceId)
                .orElseThrow(() -> new NotFoundException("SousCompetence not found with id: " + sousCompetenceId));

        question.setSousCompetence(sousCompetence);
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question question) {
        Question existingQuestion = getQuestionById(id);
        existingQuestion.setEnonce(question.getEnonce());
        existingQuestion.setCorrectAnswer(question.getCorrectAnswer());

        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(Long id) {
        Question question = getQuestionById(id);
        questionRepository.delete(question);
    }

    //------------- function get list questions by SousCompetenceID -- ///
      public List<Question> getQuestionBySousCompetenceId(Long sousCompetenceId) {
        return questionRepository.findBySousCompetenceId(sousCompetenceId);
    }
//-------------------------------------------------------------------------------------------------//
    //------------- function get questionID by SousCompetenceID -- ///
    public Question getQuestionBySousCompetenceAndQuestionId(Long sousCompetenceId, Long questionId) {
        List<Question> questions = questionRepository.findBySousCompetenceId(sousCompetenceId);
        Optional<Question> foundQuestion = questions.stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst();

        if (foundQuestion.isPresent()) {
            return foundQuestion.get();
        } else {
            throw new NotFoundException("Question not found with id " + questionId + " in SousCompetence with id " + sousCompetenceId);
        }
    }
//----------------------------------------------------------------------------------------------------------------------------------------------//




}
