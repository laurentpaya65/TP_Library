package fr.training.spring.library.infrastructure;

import fr.training.spring.library.domain.enumeration.Type;
import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.infrastructure.entiteJpa.BibliothequeJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliothequeJpaRepository extends CrudRepository<BibliothequeJpa,Long> {

    List<BibliothequeJpa> findAllByType(Type type);

    List<BibliothequeJpa> findAllByDirecteurJpaPrenom(String prenom);

    List<BibliothequeJpa> findAllByAdresseJpa_CodePostal(String codePostal);

    @Query("SELECT b FROM BIBLIOTHEQUE b WHERE b.directeurJpa.prenom = ?1")
    List<BibliothequeJpa> searchByDirecteurJpaPrenomWithJPQLQuery(String prenom);
    @Query("SELECT b FROM BIBLIOTHEQUE b WHERE b.adresseJpa.codePostal = ?1")
    List<BibliothequeJpa> searchBycodePostal(String codePostal);
//    @Query(value = "SELECT * FROM BIBLIOTHEQUE WHERE DIRECTEURJPA_PRENOM = :prenom ",nativeQuery = true)
//    List<BibliothequeJpa> searchByDirecteurJpaPrenomWithNativeQuery(String prenom);
}
