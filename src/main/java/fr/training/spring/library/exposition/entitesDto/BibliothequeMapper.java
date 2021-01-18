package fr.training.spring.library.exposition.entitesDto;

import fr.training.spring.library.domain.Bibliotheque;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BibliothequeMapper extends AbstractMapper<BibliothequeDto, Bibliotheque> {

    private final AdresseMapper adresseMapper;
    private final DirecteurMapper directeurMapper;
    private final LivreMapper livreMapper;

    public BibliothequeMapper(AdresseMapper adresseMapper, DirecteurMapper directeurMapper, LivreMapper livreMapper) {
        this.adresseMapper = adresseMapper;
        this.directeurMapper = directeurMapper;
        this.livreMapper = livreMapper;
    }

    @Override
    public BibliothequeDto mapToDto(Bibliotheque entity) {
        if (entity == null) {
            return null;
        }
        final BibliothequeDto dto = new BibliothequeDto();
        dto.setAdresseDto(adresseMapper.mapToDto(entity.getAdresse()));
        dto.setDirecteurDto(directeurMapper.mapToDto(entity.getDirecteur()));
        dto.setId(entity.getId());
        dto.setLivreDtos(livreMapper.mapToDtoList(entity.getLivres()));
        dto.setType(entity.getType());

        return dto;
    }

    @Override
    public Bibliotheque mapToEntity(BibliothequeDto dto) {
        final Bibliotheque bibliotheque = Bibliotheque.builder()
                .livres(livreMapper.mapToEntityList(dto.getLivreDtos()))
                .directeur(directeurMapper.mapToEntity(dto.getDirecteurDto()))
                .adresse(adresseMapper.mapToEntity(dto.getAdresseDto()))
                .type(dto.getType())
                .id(dto.getId())
                .build();
        return bibliotheque;
    }


}
