package fr.training.spring.library.domain;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.enumeration.Type;

import java.util.List;

@DDD.Repository
public interface BibliothequeRepository {

    public Bibliotheque save(Bibliotheque bibliotheque);

    public Bibliotheque chercheParId(Long id);

    public void supprimerParId(Long id);

    public List<Bibliotheque> toutesBibliotheques();

    public List<Bibliotheque> bibliothequesParType(Type type);

    public List<Bibliotheque> bibliothequesParPrenom(String prenom);

    public List<Bibliotheque> bibliothequesParCodePostal(String CodePostal);
}
