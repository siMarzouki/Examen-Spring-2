package tn.esprit.Services;
import tn.esprit.DAO.Entities.*;

import java.util.Set;

public interface IServices {
    public Beneficiaire ajouterBeneficiaire (Beneficiaire bf);
    public Contrat ajouterContrat (Contrat c);
    public Assurance ajouterAssurance (Assurance a, int cinBf, String matricule);
    public Contrat getContratBf (int idBf);
    public Set<Beneficiaire> getBeneficairesByType (TypeContrat typeContrat);

    public float getMontantBf (int cinBf);
    public void statistiques ();

}
