package fr.training.spring.library.infrastructure.entiteJpa;

import fr.training.spring.library.domain.Adresse;

import org.springframework.stereotype.Component;

@Component
public class AdresseMapperJpa extends AbstractMapperJpa<AdresseJpa,Adresse> {

    @Override
    public AdresseJpa mapToJpa(Adresse entity) {
        if (entity == null) {
            return null;
        }
        final AdresseJpa jpa = new AdresseJpa();
        jpa.setCodePostal(entity.getCodePostal());
        jpa.setNumero(entity.getNumero());
        jpa.setRue(entity.getRue());
        jpa.setVille(entity.getVille());
        return jpa;
    }

    @Override
    public Adresse mapToEntity(AdresseJpa jpa) {
        if (jpa == null) {
            return null;
        }
        final Adresse adresse = new Adresse(jpa.getNumero(), jpa.getRue(),
                jpa.getCodePostal(), jpa.getVille());
        return adresse;
    }
}
