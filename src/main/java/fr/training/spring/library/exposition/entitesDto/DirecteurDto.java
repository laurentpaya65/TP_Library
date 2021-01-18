package fr.training.spring.library.exposition.entitesDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.exception.ErrorCodes;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class DirecteurDto implements Serializable {
    @Size(min=1,message = ErrorCodes.DIRECTEUR_NAME_HAVE_MORE_THAN_ONE_CARACTER)
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("nom")
    private String nom;

    public DirecteurDto() {}

    public DirecteurDto(String prenom, String nom) {
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
