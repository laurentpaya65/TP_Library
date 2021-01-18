package fr.training.spring.library.infrastructure.entiteJpa;

import fr.training.spring.library.domain.Livre;

import org.springframework.stereotype.Component;

@Component
public class LivreMapperJpa extends AbstractMapperJpa<LivreJpa, Livre> {
    @Override
    public LivreJpa mapToJpa(Livre entity) {
        if (entity == null) {
            return null;
        }
        final LivreJpa jpa = new LivreJpa();
        jpa.setAuteur(entity.getAuteur());
        jpa.setGenre(entity.getGenre());
        jpa.setId(entity.getId());
        jpa.setIsbn(entity.getIsbn());
        jpa.setPageNumber(entity.getPageNumber());
        jpa.setTitre(entity.getTitre());
        return jpa;
    }

    @Override
    public Livre mapToEntity(LivreJpa jpa) {
        if (jpa == null) {
            return null;
        }
        final Livre livre = new Livre(jpa.getId(), jpa.getIsbn(), jpa.getTitre(),
                jpa.getAuteur(), jpa.getPageNumber(), jpa.getGenre());
        return livre;
    }

}
