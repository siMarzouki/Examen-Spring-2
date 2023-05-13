package tn.esprit.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.Entities.Assurance;
import tn.esprit.DAO.Entities.Beneficiaire;
import tn.esprit.DAO.Entities.Contrat;
import tn.esprit.DAO.Entities.TypeContrat;
import tn.esprit.Services.IServices;

import java.util.Set;

@RestController
@AllArgsConstructor
public class RestControllers {
    @Autowired
    IServices services;

    @PostMapping("/ajouterBeneficiaire")
    public Beneficiaire ajouterBeneficiaire(@RequestBody  Beneficiaire bf) {
        return services.ajouterBeneficiaire(bf);
    }
    @PostMapping("/ajouterContrat")
    public Contrat ajouterContrat(@RequestBody Contrat c){
        return services.ajouterContrat(c);
    }

    @PostMapping("/ajouterAssurance")
    public Assurance ajouterAssurance(@RequestBody Assurance a, @RequestParam int cinBf,@RequestParam String matricule){
        return services.ajouterAssurance(a,cinBf,matricule);
    }
    @GetMapping("/getContratBf")
    public Contrat getContratBf(@RequestParam int idBf){
        return services.getContratBf(idBf);
    }
     @GetMapping("/getBeneficairesByType")
    public Set<Beneficiaire> getBeneficairesByType(@RequestParam TypeContrat typeContrat){
        return services.getBeneficairesByType(typeContrat);
    }

    @GetMapping("/getMontantBf")
    public float getMontantBf(@RequestParam int cinBf){
        return services.getMontantBf(cinBf);
    }
}
