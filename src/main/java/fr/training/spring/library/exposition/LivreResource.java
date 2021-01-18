package fr.training.spring.library.exposition;

import fr.training.spring.library.application.LivreService;
import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.enumeration.GenreLitteraire;
import fr.training.spring.library.exposition.entitesDto.LivreDto;
import fr.training.spring.library.exposition.entitesDto.LivreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LivreResource {

    @Autowired
    private LivreService livreService;
    @Autowired
    LivreMapper livreMapper;

    // adresse dans INFRA/OpenLibraryRestConfig = "https://openlibrary.org/isbn/" + isbn + ".json""
    // ex d'ISBN (dans onglet Overview)=  0300097409 pour DaVinciCode
    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public LivreDto searchlivreByISBN(String isbn) {
        final Livre livre = livreService.searchlivreByISBN(isbn);
        return livreMapper.mapToDto(livre);
    }

    @PostMapping("/ajoutlivre")
    @ResponseStatus(HttpStatus.OK)
    public void ajoutLivre(@RequestParam Long bibId, String isbn, GenreLitteraire genre) {
        livreService.enregistreLivre(bibId,isbn,genre);
    }
}
