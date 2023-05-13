package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Assurance;

public interface AssuranceRepo extends JpaRepository<Assurance,Integer> {
}
