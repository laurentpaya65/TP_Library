package fr.training.spring.library.infrastructure.entiteJpa;

import fr.training.spring.library.domain.Directeur;
import org.springframework.stereotype.Component;

@Component
public class DirecteurMapperJpa extends AbstractMapperJpa<DirecteurJpa,Directeur> {
    @Override
    public DirecteurJpa mapToJpa(Directeur entity) {
        if (entity == null) {
            return null;
        }
        final DirecteurJpa  jpa = new DirecteurJpa();
        jpa.setNom(entity.getNom());
        jpa.setPrenom(entity.getPrenom());
        return jpa;
    }

    @Override
    public Directeur mapToEntity(DirecteurJpa jpa) {
        if (jpa == null) {
            return null;
        }
        final Directeur directeur = new Directeur(jpa.getPrenom(), jpa.getNom());
        return directeur;
    }
}
