package fr.training.spring.library.domain;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.exception.ControlException;
import fr.training.spring.library.domain.exception.ErrorCodes;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
@DDD.ValueObject
public class Directeur implements Serializable {
    private String prenom;
    private String nom;

    public Directeur() {}

    public Directeur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public void validate() {
        if (StringUtils.isEmpty(prenom) || StringUtils.isEmpty(nom) ) {
            throw new ControlException("prenom et nom doivent être renseignés", ErrorCodes.DIRECTEUR_NOM_PRENOM_NOT_VIDE);

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directeur directeur = (Directeur) o;
        return Objects.equals(prenom, directeur.prenom) &&
                Objects.equals(nom, directeur.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenom, nom);
    }
}
