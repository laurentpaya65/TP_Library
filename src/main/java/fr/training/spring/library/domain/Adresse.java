package fr.training.spring.library.domain;

import fr.training.spring.library.domain.ddd.DDD;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
@DDD.ValueObject
public class Adresse implements Serializable {
    private int numero;
    private String rue;
    private int codePostal;
    private String ville;

    public Adresse(){}

    public Adresse(int numero, String rue, int codePostal, String ville) {
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getNumero() {
        return numero;
    }

    public String getRue() {
        return rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return numero == adresse.numero &&
                codePostal == adresse.codePostal &&
                Objects.equals(rue, adresse.rue) &&
                Objects.equals(ville, adresse.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, rue, codePostal, ville);
    }
}
