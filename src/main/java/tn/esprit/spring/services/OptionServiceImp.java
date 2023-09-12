package tn.esprit.spring.services;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.NotFoundException;
import tn.esprit.spring.entity.Option;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.repositories.OptionRepository;
import tn.esprit.spring.repositories.QuestionRepository;

import java.util.List;


@AllArgsConstructor
@Service
public class OptionServiceImp implements IQuestionService{


    private OptionRepository optionRepository;
    private QuestionRepository questionRepository;

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Option getOptionById(Long id) {
        return optionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Option not found with id: " + id));
    }

    public Option createOption(Long questionId, Option option) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + questionId));

        option.setQuestion(question);
        return optionRepository.save(option);
    }

    public Option updateOption(Long id, Option option) {
        Option existingOption = getOptionById(id);
        existingOption.setOption(option.getOption());

        return optionRepository.save(existingOption);
    }

    public void deleteOption(Long id) {
        Option option = getOptionById(id);
        optionRepository.delete(option);
    }


    //------------- function get list questions by SousCompetenceID -- ///
    public List<Option> getOptionByQuestionId(Long questionId) {
        return optionRepository.findByQuestion_Id(questionId);
    }
//-------------------------------------------------------------------------------------------------//

}
