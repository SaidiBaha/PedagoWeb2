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
public class SousCompetence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;



    @JsonIgnore
    @OneToMany(mappedBy = "sousCompetence")
    private List<Question> questions;




}
