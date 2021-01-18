package fr.training.spring.library.exposition.entitesDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractMapper<T,S> {

    public abstract T mapToDto(S entity);

    public abstract S mapToEntity(T dto);

    public List<T> mapToDtoList(final List<S> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Map a Dto list to an entity list
     *
     * @param dtoList dtoList
     * @return a List of the mapped entity
     */
    public List<S> mapToEntityList(final List<T> dtoList) {
        return dtoList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
    }
}
