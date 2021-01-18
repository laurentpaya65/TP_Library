package fr.training.spring.library.application;

import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.LivreRepository;
import fr.training.spring.library.domain.enumeration.GenreLitteraire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private ServiceBibliotheque serviceBibliotheque;

    public Livre searchlivreByISBN(String isbn) {
        return livreRepository.searchlivreByISBN(isbn);
    }

    public void enregistreLivre(Long bibliothequeId, String isbn, GenreLitteraire genre) {
        Bibliotheque bib = serviceBibliotheque.findById(bibliothequeId);
        Livre livre = searchlivreByISBN(isbn);
        Livre livreMaj = new Livre(null, livre.getIsbn(), livre.getTitre(),livre.getAuteur(),
                livre.getPageNumber(),genre);
        bib.getLivres().add(livreMaj);
        serviceBibliotheque.update(bib);
    }
}
