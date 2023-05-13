package tn.esprit.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.DAO.Entities.Assurance;
import tn.esprit.DAO.Entities.Beneficiaire;
import tn.esprit.DAO.Entities.Contrat;
import tn.esprit.DAO.Entities.TypeContrat;
import tn.esprit.DAO.Repositories.AssuranceRepo;
import tn.esprit.DAO.Repositories.BeneficiaireRepo;
import tn.esprit.DAO.Repositories.ContratRepo;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class Services implements IServices {
    AssuranceRepo assuranceRepo;
    BeneficiaireRepo beneficiaireRepo;
    ContratRepo contratRepo;

    @Override
    public Beneficiaire ajouterBeneficiaire(Beneficiaire bf) {
        return beneficiaireRepo.save(bf);
    }

    @Override
    public Contrat ajouterContrat(Contrat c) {
        return contratRepo.save(c);
    }

    @Override
    public Assurance ajouterAssurance(Assurance a, int cinBf, String matricule) {
        Beneficiaire b = beneficiaireRepo.findByCin(cinBf);
        Contrat c = contratRepo.findByMatricule(matricule);
        b.getAssurances().add(a);
        beneficiaireRepo.save(b);
        a.setBeneficiaire(b);
        a.setContrat(c);
        return assuranceRepo.save(a);
    }

    @Override
    public Contrat getContratBf(int idBf) {
        Beneficiaire b = beneficiaireRepo.findById(idBf).get();
        List<Assurance> assuranceList =  b.getAssurances();
        return assuranceList
                .stream()
                .sorted((a1, a2) -> a1.getContrat().getDateEffet().compareTo(a2.getContrat().getDateEffet()))
                .findFirst().get().getContrat();
    }

    @Override
    public Set<Beneficiaire> getBeneficairesByType(TypeContrat typeContrat) {
        Set<Beneficiaire> res=new HashSet<Beneficiaire>();
        assuranceRepo.findAll()
                .stream()
                .filter(ass -> ass.getContrat().getType().equals(typeContrat))
                .forEach(ass -> {
                    res.add(ass.getBeneficiaire());
                });
        return res;
    }

    @Override
    public float getMontantBf(int cinBf) {
        Beneficiaire b = beneficiaireRepo.findByCin(cinBf);
        return (float) b.getAssurances()
                .stream()
                .mapToDouble(assurance -> {
                    Contrat c = assurance.getContrat();
                    switch (c.getType()){
                        case ANNUEL:
                            return assurance.getMontant();
                        case MENSUEL:
                            return assurance.getMontant()*12;
                        case SEMESTRIEL:
                            return assurance.getMontant()*2;
                        default:
                            break;
                    }
                    return 0;
                }).sum();
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void statistiques() {
        TreeMap<Integer, List<Integer>> myStat = new TreeMap<>(Collections.reverseOrder());
        List<Beneficiaire> beneficiaires=beneficiaireRepo.findAll();
        beneficiaires.forEach(beneficiaire -> {
            int size = beneficiaire.getAssurances().size();
            List<Integer> cinsOfThisSize = myStat.get(size);
            if(cinsOfThisSize==null){
                cinsOfThisSize=new ArrayList<Integer>();
            }
            cinsOfThisSize.add(beneficiaire.getCin());
           myStat.put(size,cinsOfThisSize);
        });

        myStat.forEach((nb,cins) ->{
            cins.forEach(cin->{
                log.info("Benf : "+cin+"  assurances : "+nb);
            });

        } );

    }
}
