package fr.training.spring.library.infrastructure.entiteJpa;

import javax.persistence.Embeddable;

@Embeddable
public class DirecteurJpa  {
    private String prenom;
    private String nom;

    public DirecteurJpa() {}

    public DirecteurJpa(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
