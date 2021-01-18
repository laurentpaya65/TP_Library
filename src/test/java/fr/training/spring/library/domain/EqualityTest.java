package fr.training.spring.library.domain;

import fr.training.spring.library.exposition.entitesDto.BibliothequeDto;
import fr.training.spring.library.exposition.entitesDto.BibliothequeMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static fr.training.spring.library.exposition.BaseHelper.LIBRARY_EGAL1;
import static fr.training.spring.library.exposition.BaseHelper.LIBRARY_EGAL2;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EqualityTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private BibliothequeMapper bibliothequeMapper;

    @Test
    @DisplayName("Egalité sur la classe Bibliotheque")
    void test_egalite() {
        //---------- Given ------------------
        //---------- Then ------------------
        ResponseEntity<BibliothequeDto> responseEntity1= testRestTemplate
                .postForEntity("/create", LIBRARY_EGAL1,BibliothequeDto.class);
        Bibliotheque bib_EGAL1 = bibliothequeMapper.mapToEntity(responseEntity1.getBody());

        ResponseEntity<BibliothequeDto> responseEntity2= testRestTemplate
                .postForEntity("/create", LIBRARY_EGAL2, BibliothequeDto.class);
        Bibliotheque bib_EGAL2 = bibliothequeMapper.mapToEntity(responseEntity2.getBody());
        //---------- Result------------------
        System.out.println("bib_EGAL1.id="+bib_EGAL1.getId()+"//"+"bib_EGAL2.id="+bib_EGAL2.getId());

        assertThat(bib_EGAL1).isNotEqualTo(bib_EGAL2);
        assertThat(bib_EGAL1.equalsValue(bib_EGAL2)).isTrue();
    }
}
