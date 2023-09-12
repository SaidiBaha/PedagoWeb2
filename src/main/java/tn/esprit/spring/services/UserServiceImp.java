package tn.esprit.spring.services;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.esprit.spring.entity.*;
import tn.esprit.spring.repositories.*;

import java.util.List;

@AllArgsConstructor

@Service
public class UserServiceImp  implements IUserService{

    private UserRepository userRepository;
    private AnswerRepository answerRepository;


    private ScoreRepository scoreRepository;



    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }

    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User existingUser = getUserById(id);
        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setEmailAddress(user.getEmailAddress());
        existingUser.setBirthDate(user.getBirthDate());
       // existingUser.setPassword(user.getPassword());
        existingUser.setNiveau(user.getNiveau());
        existingUser.setRole(user.getRole());

        if (!user.getPassword().equals(existingUser.getPassword())) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            existingUser.setPassword(encodedPassword);
        }


        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
//*******************    function answerQuestion ************************//
  private QuestionServiceImp questionService ;
    public String answerQuestion(Long userId, Long sousCompetenceId, Long questionId, String answerText) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        Question question = questionService.getQuestionBySousCompetenceAndQuestionId(sousCompetenceId, questionId);

        // Vérification de la réponse
        boolean isAnswerCorrect = answerText.equalsIgnoreCase(question.getCorrectAnswer());

        Answer answer = new Answer();
        System.out.println(answer);
        answer.setAnswer(answerText);
        answer.setQuestion(question);
        Answer savedAnswer = answerRepository.save(answer);
        Long savedAnswerId = savedAnswer.getId();

        // Mise à jour des points de l'utilisateur si la réponse est correcte
        Score score = new Score();
        int scores = score.getScore() != null ? score.getScore() : 0;
        if (isAnswerCorrect) {

            score.setScore(1);
            answer.setAnswer(answerText);
            score.setUser(user);
            score.setAnswer(answer);
            scoreRepository.save(score);
            return "ok";
        } else {
            score.setScore(0); // Change this value as needed

            // Associer la réponse à l'utilisateur et à la question
            score.setUser(user);
            score.setAnswer(savedAnswer); // Utilisez la réponse enregistrée
            scoreRepository.save(score);

            return "no";
        }
    }
                //********* fonction pour calcule score *****//


    public Integer getUserTotalScore(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        List<Score> scores = user.getScores();
        int totalScore = 0;

        for (Score score : scores) {
            totalScore += score.getScore();
        }

        return totalScore;
    }

}
