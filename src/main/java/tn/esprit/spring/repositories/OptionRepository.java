package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Option;
import tn.esprit.spring.entity.Question;

import java.util.List;


@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option>findByQuestion_Id(Long QuestionId) ;
}
