package fr.training.spring.library.exposition.entitesDto;

import fr.training.spring.library.domain.Livre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivreMapper extends AbstractMapper<LivreDto, Livre> {
    @Override
    public LivreDto mapToDto(Livre entity) {
        if (entity == null) {
            return null;
        }
        final LivreDto dto = new LivreDto();
        dto.setAuteur(entity.getAuteur());
        dto.setGenre(entity.getGenre());
        dto.setId(entity.getId());
        dto.setIsbn(entity.getIsbn());
        dto.setPageNumber(entity.getPageNumber());
        dto.setTitre(entity.getTitre());
        return dto;
    }

    @Override
    public Livre mapToEntity(LivreDto dto) {
        if (dto == null) {
            return null;
        }
        final Livre livre = new Livre(dto.getId(), dto.getIsbn(), dto.getTitre(),
                dto.getAuteur(), dto.getPageNumber(),dto.getGenre());
        return livre;
    }


}
