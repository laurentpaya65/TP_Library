package fr.training.spring.library.infrastructure.entiteJpa;

import fr.training.spring.library.domain.enumeration.GenreLitteraire;

import javax.persistence.*;


@Entity(name = "LIVRE")
public class LivreJpa  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    private String isbn;
    private String titre;
    private String auteur;
    private int pageNumber;

    @Enumerated(EnumType.STRING)
    private GenreLitteraire genre;

    public LivreJpa() {}

    public LivreJpa(Long id, String isbn, String titre, String auteur, int pageNumber, GenreLitteraire genre) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public GenreLitteraire getGenre() {
        return genre;
    }

    public void setGenre(GenreLitteraire genre) {
        this.genre = genre;
    }
}
