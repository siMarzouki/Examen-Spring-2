package tn.esprit.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idBenef;

    int cin;
    String nom;
    String prenom;
    String profession;
    float salaire;
    @JsonIgnore
    @OneToMany(mappedBy = "beneficiaire", fetch=FetchType.EAGER)
    List<Assurance> assurances;

}
