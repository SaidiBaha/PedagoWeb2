package tn.esprit.spring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "score")
    Integer score= 0;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "answer_id")
    private Answer answer;



}
