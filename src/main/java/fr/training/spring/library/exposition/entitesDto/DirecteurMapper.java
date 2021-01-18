package fr.training.spring.library.exposition.entitesDto;

import fr.training.spring.library.domain.Directeur;
import org.springframework.stereotype.Component;

@Component
public class DirecteurMapper extends AbstractMapper<DirecteurDto, Directeur> {
    @Override
    public DirecteurDto mapToDto(Directeur entity) {
        if (entity == null) {
            return null;
        }
        final DirecteurDto dto = new DirecteurDto();
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        return dto;
    }

    @Override
    public Directeur mapToEntity(DirecteurDto dto) {
        if (dto == null ) {
            return null;
        }
        final Directeur directeur = new Directeur(dto.getPrenom(), dto.getNom());
        return directeur;
    }
}
