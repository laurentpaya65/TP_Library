package fr.training.spring.library.exposition.entitesDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AdresseDto implements Serializable {
    @JsonProperty("numero")
    private int numero;
    @JsonProperty("rue")
    private String rue;
    @JsonProperty("codePostal")
    private int codePostal;
    @JsonProperty("ville")
    private String ville;

    public AdresseDto() {}

    public AdresseDto(int numero, String rue, int codePostal, String ville) {
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
