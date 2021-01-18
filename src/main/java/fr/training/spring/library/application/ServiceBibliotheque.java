package fr.training.spring.library.application;

import fr.training.spring.library.domain.BibliothequeRepository;
import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.enumeration.Type;
import fr.training.spring.library.domain.Bibliotheque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DDD.ApplicationService
@Service
public class ServiceBibliotheque {
    @Autowired
    private BibliothequeRepository bibliothequeRepository;

//    public ServiceBibliotheque(BibliothequeRepository bibliothequeRepository) {
//        this.bibliothequeRepository = bibliothequeRepository;
//    }

    @Transactional
    public Bibliotheque create(Bibliotheque bibliotheque) {
        return bibliothequeRepository.save(bibliotheque);
    }
    @Transactional
    public Bibliotheque update(Bibliotheque bibliotheque) {
        Bibliotheque biblio1  = findById(bibliotheque.getId());
        biblio1.update(bibliotheque);
        return bibliothequeRepository.save(biblio1);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        bibliothequeRepository.supprimerParId(id);
    }
    @Transactional(readOnly = true)
    public Bibliotheque findById(Long id) {
//        return bibliothequeJpaRepository.findById(id).get();
        return bibliothequeRepository.chercheParId(id);
    }
    @Transactional(readOnly = true)
    public  List<Bibliotheque> findAll() {
        return (List<Bibliotheque>) bibliothequeRepository.toutesBibliotheques();
    }
    @Transactional(readOnly = true)
    public List<Bibliotheque> findAllByType(Type type) {
        return bibliothequeRepository.bibliothequesParType(type);
    }
    @Transactional(readOnly = true)
    public List<Bibliotheque> finaAllByDirecteurSurname(String prenom) {
        return bibliothequeRepository.bibliothequesParPrenom(prenom);
    }
    @Transactional(readOnly = true)
    public List<Bibliotheque> finaAllByCodePostal(String codePostal) {
        return bibliothequeRepository.bibliothequesParCodePostal(codePostal);
    }


}
