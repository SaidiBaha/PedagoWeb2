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
public class Competence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "thematique_id")
    private Thematique thematique;




    @JsonIgnore
    @OneToMany(mappedBy = "competence")
    private List<SousCompetence> sousCompetences;






}
