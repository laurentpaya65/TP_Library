package fr.training.spring.library.domain;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.enumeration.Type;
import fr.training.spring.library.domain.exception.ControlException;
import fr.training.spring.library.domain.exception.NotFoundException;
import fr.training.spring.library.domain.exception.ErrorCodes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@Entity
@DDD.Entity
public class Bibliotheque {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;
//    @Embedded
    private Adresse adresse;
//    @Embedded
    private Directeur directeur;

//    @ManyToMany(targetEntity = Livre.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Livre> livres = new ArrayList<>();

    public Bibliotheque() {
    }

    public Bibliotheque(Long id, Type type, Adresse adresse, Directeur directeur, List<Livre> livres) {
        this.id = id;
        this.type = type;
        this.adresse = adresse;
        this.directeur = directeur;
        this.livres = livres;
        validate();
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public Type getType() {
        return type;
    }

//    public void setType(Type type) {
//        this.type = type;
//    }

    public Adresse getAdresse() {
        return adresse;
    }

//    public void setAdresse(Adresse adresse) {
//        this.adresse = adresse;
//    }

    public Directeur getDirecteur() {
        return directeur;
    }

//    public void setDirecteur(Directeur directeur) {
//        this.directeur = directeur;
//    }

    public List<Livre> getLivres() {
        return livres;
    }

//    public void setLivres(List<Livre> livres) {
//        this.livres = livres;
//    }

    public void update(Bibliotheque bibliotheque){
        if (bibliotheque == null) {
            throw new NotFoundException("biblio non reçue", ErrorCodes.BIBLIOTHEQUE_NOT_FOUND);
        }
        if (bibliotheque.getAdresse() != null) {
            this.adresse = bibliotheque.getAdresse();
        }
        if (bibliotheque.getDirecteur() != null) {
            this.directeur = bibliotheque.getDirecteur();
        }
        if (bibliotheque.getType() != null) {
            this.type = bibliotheque.getType();
        }
        if (bibliotheque.getLivres() != null) {
            this.livres = bibliotheque.getLivres();
        }
        validate();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bibliotheque that = (Bibliotheque) obj;
        return Objects.equals(id, that.id) ;
    }

    public boolean equalsValue(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bibliotheque that = (Bibliotheque) o;
//        return Objects.equals(id, that.id) &&
        return        type == that.type &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(directeur, that.directeur) &&
                Objects.equals(livres, that.livres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, adresse, directeur, livres);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bibliotheque{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", adresse=").append(adresse);
        sb.append(", directeur=").append(directeur);
        sb.append(", livres=").append(livres);
        sb.append('}');
        return sb.toString();
    }

    public void validate() {
        this.directeur.validate();
    }

    private Bibliotheque(Builder builder){
        if (builder.id != null) {
            id = builder.id;
        }
        type = builder.type;
        adresse = builder.adresse;
        directeur = builder.directeur;
        livres = builder.livres;
    }
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Long id;
        private Type type;
        private Adresse adresse;
        private Directeur directeur;
        private List<Livre> livres = new ArrayList<>();

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }
        public Builder type(final Type type){
            this.type = type;
            return this;
        }
        public Builder adresse(final Adresse adresse){
            this.adresse = adresse;
            return this;
        }
        public Builder directeur(final Directeur directeur) {
            this.directeur = directeur;
            return this;
        }
        public Builder livres(final List<Livre> livres) {
            this.livres = livres;
            return this;
        }
        public Bibliotheque build()  {
            if (adresse == null) {
                throw new ControlException("adresse is required",ErrorCodes.ADRESSE_MUST_BE_PRESENT);
            }
            if (directeur == null) {
                throw new ControlException("directeur is required",ErrorCodes.DIRECTEUR_MUST_BE_PRESENT);
            }
            if (livres == null || livres.size() == 0 ) {
                throw new ControlException("1 livre au moins",ErrorCodes.ONE_LIVRE_OR_MORE);
            }
            return new Bibliotheque(this);
        }
    }
}
