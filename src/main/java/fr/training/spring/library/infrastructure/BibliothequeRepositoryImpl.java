package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.BibliothequeRepository;
import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.enumeration.Type;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.infrastructure.entiteJpa.BibliothequeJpa;
import fr.training.spring.library.infrastructure.entiteJpa.BibliothequeMapperJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@DDD.RepositoryImpl
@Repository
public class BibliothequeRepositoryImpl implements BibliothequeRepository {
    @Autowired
    private BibliothequeJpaRepository bibliothequeJpaRepository;
    @Autowired
    private BibliothequeMapperJpa bibliothequeMapperJpa;

    @Override
    public Bibliotheque save(Bibliotheque bibliotheque) {
        return bibliothequeMapperJpa.mapToEntity(bibliothequeJpaRepository.save(bibliothequeMapperJpa.mapToJpa(bibliotheque)));
    }

    @Override
    public Bibliotheque chercheParId(Long id) {
        return bibliothequeMapperJpa.mapToEntity(bibliothequeJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Biblio not found", ErrorCodes.BIBLIOTHEQUE_NOT_FOUND)));
    }

    @Override
    public void supprimerParId(Long id) {
        bibliothequeJpaRepository.deleteById(id);
    }

    @Override
    public List<Bibliotheque> toutesBibliotheques() {
        return bibliothequeMapperJpa.mapToEntityList((List<BibliothequeJpa>) bibliothequeJpaRepository.findAll());
    }

    @Override
    public List<Bibliotheque> bibliothequesParType(Type type) {
        return bibliothequeMapperJpa.mapToEntityList( bibliothequeJpaRepository.findAllByType(type));
    }

    @Override
    public List<Bibliotheque> bibliothequesParPrenom(String prenom) {
        return bibliothequeMapperJpa.mapToEntityList( bibliothequeJpaRepository.findAllByDirecteurJpaPrenom(prenom));
//        return bibliothequeJpaRepository.findAllByDirecteurJpaPrenom(prenom);
    }

    @Override
    public List<Bibliotheque> bibliothequesParCodePostal(String CodePostal) {
        return bibliothequeMapperJpa.mapToEntityList( bibliothequeJpaRepository.findAllByAdresseJpa_CodePostal(CodePostal));
    }
}
