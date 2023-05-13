package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Beneficiaire;

public interface BeneficiaireRepo extends JpaRepository<Beneficiaire,Integer> {
    Beneficiaire findByCin(int bfcin);
}
