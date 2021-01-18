package fr.training.spring.library.exposition.entitesDto;

import fr.training.spring.library.domain.Adresse;
import org.springframework.stereotype.Component;

@Component
public class AdresseMapper extends AbstractMapper<AdresseDto, Adresse> {

    @Override
    public AdresseDto mapToDto(Adresse entity) {
        if (entity == null) {
            return null;
        }
        final AdresseDto dto = new AdresseDto();
        dto.setCodePostal(entity.getCodePostal());
        dto.setNumero(entity.getNumero());
        dto.setRue(entity.getRue());
        dto.setVille(entity.getVille());
        return dto;
    }

    @Override
    public Adresse mapToEntity(AdresseDto dto) {
        if (dto == null) {
            return null;
        }
        final Adresse adresse = new Adresse(dto.getNumero(), dto.getRue(),
                dto.getCodePostal(), dto.getVille());
        return adresse;
    }
}
