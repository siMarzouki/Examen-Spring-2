package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Contrat;

public interface ContratRepo extends JpaRepository<Contrat,Integer> {
    Contrat findByMatricule(String mat);
}
