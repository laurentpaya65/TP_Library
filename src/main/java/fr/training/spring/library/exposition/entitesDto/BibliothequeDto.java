package fr.training.spring.library.exposition.entitesDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.training.spring.library.domain.enumeration.Type;
import fr.training.spring.library.domain.exception.ErrorCodes;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BibliothequeDto implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = ErrorCodes.ADRESSE_MUST_BE_PRESENT)
    @JsonProperty("adresse")
    private AdresseDto adresseDto;

    @NotNull(message = ErrorCodes.DIRECTEUR_MUST_BE_PRESENT)
    @JsonProperty("directeur")
    private DirecteurDto directeurDto;

    @JsonProperty("livres")
    private List<LivreDto> livreDtos = new ArrayList<>();

    public BibliothequeDto() {}

    public BibliothequeDto(Long id, Type type, AdresseDto adresseDto, DirecteurDto directeurDto, List<LivreDto> livreDtos) {
        this.id = id;
        this.type = type;
        this.adresseDto = adresseDto;
        this.directeurDto = directeurDto;
        this.livreDtos = livreDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public AdresseDto getAdresseDto() {
        return adresseDto;
    }

    public void setAdresseDto(AdresseDto adresseDto) {
        this.adresseDto = adresseDto;
    }


    public DirecteurDto getDirecteurDto() {
        return directeurDto;
    }

    public void setDirecteurDto(DirecteurDto directeurDto) {
        this.directeurDto = directeurDto;
    }

    public List<LivreDto> getLivreDtos() {
        return livreDtos;
    }

    public void setLivreDtos(List<LivreDto> livreDtos) {
        this.livreDtos = livreDtos;
    }

}
