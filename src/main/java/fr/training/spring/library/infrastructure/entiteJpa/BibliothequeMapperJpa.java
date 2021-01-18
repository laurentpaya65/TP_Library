package fr.training.spring.library.infrastructure.entiteJpa;

import fr.training.spring.library.domain.Bibliotheque;

import org.springframework.stereotype.Component;

@Component
public class BibliothequeMapperJpa extends AbstractMapperJpa<BibliothequeJpa, Bibliotheque> {
    private final AdresseMapperJpa adresseMapperJpa;
    private final DirecteurMapperJpa directeurMapperJpa;
    private final LivreMapperJpa livreMapperJpa;

    public BibliothequeMapperJpa(AdresseMapperJpa adresseMapperJpa, DirecteurMapperJpa directeurMapperJpa, LivreMapperJpa livreMapperJpa) {
        this.adresseMapperJpa = adresseMapperJpa;
        this.directeurMapperJpa = directeurMapperJpa;
        this.livreMapperJpa = livreMapperJpa;
    }

    @Override
    public BibliothequeJpa mapToJpa(Bibliotheque entity) {
        if (entity == null) {
            return null;
        }
        final BibliothequeJpa jpa = new BibliothequeJpa();
        jpa.setAdresseJpa(adresseMapperJpa.mapToJpa(entity.getAdresse()));
        jpa.setDirecteurJpa(directeurMapperJpa.mapToJpa(entity.getDirecteur()));
        jpa.setId(entity.getId());
        jpa.setLivreJpas(livreMapperJpa.mapToJpaList(entity.getLivres()));
        jpa.setType(entity.getType());

        return jpa;
    }

    @Override
    public Bibliotheque mapToEntity(BibliothequeJpa jpa) {
        final Bibliotheque bibliotheque = Bibliotheque.builder()
                .livres(livreMapperJpa.mapToEntityList(jpa.getLivreJpas()))
                .directeur(directeurMapperJpa.mapToEntity(jpa.getDirecteurJpa()))
                .adresse(adresseMapperJpa.mapToEntity(jpa.getAdresseJpa()))
                .type(jpa.getType())
                .id(jpa.getId())
                .build();
        return bibliotheque;
    }

}
