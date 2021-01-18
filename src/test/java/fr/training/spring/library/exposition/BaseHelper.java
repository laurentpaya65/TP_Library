package fr.training.spring.library.exposition;

import fr.training.spring.library.domain.*;
import fr.training.spring.library.domain.enumeration.GenreLitteraire;
import fr.training.spring.library.domain.enumeration.Type;
import fr.training.spring.library.exposition.entitesDto.*;
import fr.training.spring.library.infrastructure.BibliothequeJpaRepository;
import fr.training.spring.library.infrastructure.entiteJpa.BibliothequeJpa;
import fr.training.spring.library.infrastructure.entiteJpa.BibliothequeMapperJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseHelper {
    @Autowired
    BibliothequeJpaRepository bibliothequeJpaRepository;
    @Autowired
    BibliothequeMapperJpa bibliothequeMapperJpa;
    @Autowired
    BibliothequeMapper bibliothequeMapper;

    public static final Livre LIVRE_PHILO = new Livre(0L,"ISBN-0001",
            "Les robots","Asimov",100, GenreLitteraire.PHILOSOPHIE);
    public static final Livre LIVRE_FICTION = new Livre(0L,"ISBN-0002",
            "Les plantes","Petit",120, GenreLitteraire.ETUDES);
    public static final Livre LIVRE_HISTOIRE = new Livre(0L,"ISBN-0003",
            "Histoire de France","Jean Favier",500, GenreLitteraire.HISTOIRE);
    public static final LivreDto LIVRE_DTO = new LivreDto(0L,"ISBN-0003",
            "Histoire de France","Jean Favier",500, GenreLitteraire.HISTOIRE);

    public static final Bibliotheque NATIONAL_LIBRARY_MONTREUIL = new Bibliotheque(0L, Type.SCOLAIRE,
            new Adresse(93, "Rue des Montreuil", 93100, "Montreuil"), new Directeur("Romain", "NOEL"),
            Arrays.asList(LIVRE_PHILO,LIVRE_FICTION,LIVRE_HISTOIRE)) ;
    public static final Bibliotheque DUMMY_LIBRARY = new Bibliotheque(0L, null,
            new Adresse(0, "Rue des Montreuil", 93100, "Montreuil"), new Directeur("Romain", "NOEL"),
            Arrays.asList(LIVRE_HISTOIRE)) ;
    public static final Bibliotheque NOT_UPD_LIBRARY = new Bibliotheque(1000L, null,
            new Adresse(0, "Rue des Montreuil", 93100, "Montreuil"), new Directeur("Romain", "NOEL"),
            Arrays.asList(LIVRE_HISTOIRE)) ;
    public static final BibliothequeDto DIRECTEUR_NULL_LIBRARY = new BibliothequeDto(0L, Type.ASSOCIATIVE,
            new AdresseDto(0, "Rue des Montreuil", 93100, "Montreuil"), null,
            Arrays.asList(LIVRE_DTO));
    public static final BibliothequeDto LIBRARY_EGAL1 = new BibliothequeDto(0L, Type.ASSOCIATIVE,
            new AdresseDto(0, "Rue des Montreuil", 93100, "Montreuil"),
            new DirecteurDto("Romain", "NOEL"),
            Arrays.asList(LIVRE_DTO));
    public static final BibliothequeDto LIBRARY_EGAL2 = new BibliothequeDto(0L, Type.ASSOCIATIVE,
            new AdresseDto(0, "Rue des Montreuil", 93100, "Montreuil"),
            new DirecteurDto("Romain", "NOEL"),
            Arrays.asList(LIVRE_DTO));

    void erase_bib(){
        bibliothequeJpaRepository.deleteAll();
    }
    Bibliotheque cree_bib_a_deleter() {
        return bibliothequeMapperJpa.mapToEntity(
                bibliothequeJpaRepository.save(bibliothequeMapperJpa.mapToJpa(DUMMY_LIBRARY)));
    }
    void erase_et_cree3bib(){
        erase_bib();
        bibliothequeJpaRepository.save(bibliothequeMapperJpa.mapToJpa(NATIONAL_LIBRARY_MONTREUIL));
        bibliothequeJpaRepository.save(bibliothequeMapperJpa.mapToJpa(NATIONAL_LIBRARY_MONTREUIL));
        bibliothequeJpaRepository.save(bibliothequeMapperJpa.mapToJpa(NATIONAL_LIBRARY_MONTREUIL));
    }
    List<BibliothequeDto> cree_et_lit_All_bibs() {
        erase_et_cree3bib();
        List<BibliothequeJpa> bibliothequeJpas = (List<BibliothequeJpa>) bibliothequeJpaRepository.findAll();
        List<BibliothequeDto> bibliothequeDtos = new ArrayList<>();
        for (BibliothequeJpa bibliothequeJpa : bibliothequeJpas) {
            bibliothequeDtos.add(bibliothequeMapper.mapToDto(bibliothequeMapperJpa.mapToEntity(bibliothequeJpa)));
        }
        return bibliothequeDtos;
    }
}
