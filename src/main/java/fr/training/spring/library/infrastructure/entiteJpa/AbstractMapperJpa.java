package fr.training.spring.library.infrastructure.entiteJpa;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract  class AbstractMapperJpa<T,S> {

    public abstract T mapToJpa(S entity);

    public abstract S mapToEntity(T jpa);

    public List<T> mapToJpaList(final List<S> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::mapToJpa).collect(Collectors.toList());
    }

    /**
     * Map a Jpa list to an entity list
     *
     * @param jpaList jpaList
     * @return a List of the mapped entity
     */
    public List<S> mapToEntityList(final List<T> jpaList) {
        return jpaList.stream().filter(Objects::nonNull).map(this::mapToEntity).collect(Collectors.toList());
    }
}
