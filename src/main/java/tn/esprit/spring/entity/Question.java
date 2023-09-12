package tn.esprit.spring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String enonce;
    private String correctAnswer;


    @ManyToOne
    @JoinColumn(name = "sousCompetence_id")
    @JsonIgnore
    private SousCompetence sousCompetence;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private List<Option> options;


    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private List<Answer> answers;


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", enonce='" + enonce + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                // Ne pas inclure les références à sousCompetence, options, answers ici
                '}';
    }








}
