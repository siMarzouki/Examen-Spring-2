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

public class Assurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idAssurance;
    String designation;
    float montant;

    @JsonIgnore
    @ManyToOne
    Contrat contrat;

    @JsonIgnore
    @ManyToOne
    Beneficiaire beneficiaire;
}
