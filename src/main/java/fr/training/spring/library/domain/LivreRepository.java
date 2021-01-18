package fr.training.spring.library.domain;

import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.infrastructure.http.dto.AuthorInfo;

public interface LivreRepository {

    Livre searchlivreByISBN(String isbn);

    String searchAuteur(String idAuteur);
}
