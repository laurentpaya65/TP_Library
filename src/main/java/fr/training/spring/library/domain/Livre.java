package fr.training.spring.library.domain;

import fr.training.spring.library.domain.ddd.DDD;
import fr.training.spring.library.domain.enumeration.GenreLitteraire;

import javax.persistence.*;
import java.util.Objects;

//@Entity
@DDD.Entity
public class Livre {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "ISBN")
    private String isbn;
    private String titre;
    private String auteur;
    private int pageNumber;
    @Enumerated(EnumType.STRING)
    private GenreLitteraire genre;

    public Livre() {}

    public Livre(Long id, String isbn, String titre, String auteur, int pageNumber, GenreLitteraire genre) {
        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.pageNumber = pageNumber;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public GenreLitteraire getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Livre{");
        sb.append("id=").append(id);
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", titre='").append(titre).append('\'');
        sb.append(", auteur='").append(auteur).append('\'');
        sb.append(", pageNumber=").append(pageNumber);
        sb.append(", genre=").append(genre);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return pageNumber == livre.pageNumber &&
//                Objects.equals(id, livre.id) &&
                Objects.equals(isbn, livre.isbn) &&
                Objects.equals(titre, livre.titre) &&
                Objects.equals(auteur, livre.auteur) &&
                genre == livre.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, titre, auteur, pageNumber, genre);
    }
}
