package fr.training.spring.library.infrastructure.entiteJpa;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.enumeration.Type;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@DDD.Entity
@Entity(name = "BIBLIOTHEQUE")
public class BibliothequeJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private Type type;
    @Embedded
    private AdresseJpa adresseJpa;
    @Embedded
    private DirecteurJpa directeurJpa;
    @ManyToMany(targetEntity = LivreJpa.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<LivreJpa> livreJpas = new ArrayList<>();

    public BibliothequeJpa() {}

    public BibliothequeJpa(Long id, Type type, AdresseJpa adresseJpa, DirecteurJpa directeurJpa, List<LivreJpa> livreJpas) {
        this.id = id;
        this.type = type;
        this.adresseJpa = adresseJpa;
        this.directeurJpa = directeurJpa;
        this.livreJpas = livreJpas;
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

    public AdresseJpa getAdresseJpa() {
        return adresseJpa;
    }

    public void setAdresseJpa(AdresseJpa adresseJpa) {
        this.adresseJpa = adresseJpa;
    }

    public DirecteurJpa getDirecteurJpa() {
        return directeurJpa;
    }

    public void setDirecteurJpa(DirecteurJpa directeurJpa) {
        this.directeurJpa = directeurJpa;
    }

    public List<LivreJpa> getLivreJpas() {
        return livreJpas;
    }

    public void setLivreJpas(List<LivreJpa> livreJpas) {
        this.livreJpas = livreJpas;
    }
}
