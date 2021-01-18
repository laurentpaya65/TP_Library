package fr.training.spring.library.exposition;

import fr.training.spring.library.application.ServiceBibliotheque;
import fr.training.spring.library.domain.Bibliotheque;
import fr.training.spring.library.domain.exception.ErrorCodes;
import fr.training.spring.library.exposition.entitesDto.BibliothequeDto;
import fr.training.spring.library.exposition.entitesDto.BibliothequeMapper;
import fr.training.spring.library.exposition.entitesDto.LivreMapper;
import fr.training.spring.library.infrastructure.BibliothequeJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static fr.training.spring.library.exposition.BaseHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	private BibliothequeMapper bibliothequeMapper;
	@Autowired
	private ServiceBibliotheque serviceBibliotheque;
	@Autowired
	private BaseHelper baseHelper;
	@Autowired
	private BibliothequeJpaRepository bibliothequeJpaRepository;
	@Autowired
	private LivreMapper livreMapper;

//	public static final Livre LIVRE_PHILO = new Livre(0L,"ISBN-0001",
//			"Les robots","Asimov",100, GenreLitteraire.PHILOSOPHIE);
//	public static final Livre LIVRE_FICTION = new Livre(0L,"ISBN-0002",
//			"Les plantes","Petit",120, GenreLitteraire.ETUDES);
//	public static final Livre LIVRE_HISTOIRE = new Livre(0L,"ISBN-0003",
//			"Histoire de France","Jean Favier",500, GenreLitteraire.HISTOIRE);
//
//	public static final Bibliotheque NATIONAL_LIBRARY_MONTREUIL = new Bibliotheque(0L, Type.SCOLAIRE,
//			new Adresse(93, "Rue des Montreuil", 93100, "Montreuil"), new Directeur("Romain", "NOEL"),
//			Arrays.asList(LIVRE_PHILO,LIVRE_FICTION,LIVRE_HISTOIRE)) ;
//	public static final Bibliotheque DUMMY_LIBRARY = new Bibliotheque(0L, null,
//			new Adresse(0, "Rue des Montreuil", 93100, "Montreuil"), new Directeur("Romain", "NOEL"),
//			Arrays.asList()) ;

	@BeforeEach
	public void setUp() {}

	@Nested
	@DisplayName("API POST /create")
	class Test_create {
		@Test
		@DisplayName("Api POST /create  ==> création d'une bibliotheque avec Directeur = null")
		void apiCreate_Directeur_null() {
			//---------- when ------------------
//			BibliothequeDto dto = bibliothequeMapper.mapToDto(NATIONAL_LIBRARY_MONTREUIL);
			//---------- Then API de création  ------------------
			ResponseEntity<String> responseEntity = testRestTemplate
					.postForEntity("/create", DIRECTEUR_NULL_LIBRARY, String.class);
			//----------- test ---------------------
			assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
			assertThat(responseEntity.getBody()).contains(ErrorCodes.DIRECTEUR_MUST_BE_PRESENT);
		}
		@Test
		@DisplayName("Api POST /create  ==> création d'une bibliotheque valide")
		void apiCreate() {
			//---------- when ------------------
			BibliothequeDto dto = bibliothequeMapper.mapToDto(NATIONAL_LIBRARY_MONTREUIL);
			//---------- Then API de création  ------------------
			ResponseEntity<BibliothequeDto> responseEntity = testRestTemplate
					.postForEntity("/create", dto, BibliothequeDto.class);
			//----------- test ---------------------
			assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
			System.out.println("bibliotheque=" + bibliothequeMapper.mapToEntity(responseEntity.getBody()).toString());
			System.out.println("NATIONAL_LIBRARY_MONTREUIL=" + NATIONAL_LIBRARY_MONTREUIL.toString());
			assertThat(bibliothequeMapper.mapToEntity(responseEntity.getBody())
					.equalsValue(NATIONAL_LIBRARY_MONTREUIL)).isTrue();
		}
	}

	@Nested
	@DisplayName("API GET /affichebibliotheque")
	class Test_Get_Bib {
		@Test
		@DisplayName("Api GET /affichebibliotheque ==> TROUVE une biblio sur Id")
		void apiGetBibParId() {
			//---------- when ------------------
			System.out.println(NATIONAL_LIBRARY_MONTREUIL.getLivres().get(2));

			Bibliotheque bib = serviceBibliotheque.create(NATIONAL_LIBRARY_MONTREUIL);
//		ResponseEntity<Bibliotheque> responseEntity = testRestTemplate.postForEntity("/create",NATIONAL_LIBRARY_MONTREUIL,Bibliotheque.class);
//		Long idCree = responseEntity.getBody().getId();

			//---------- Then ------------------
			ResponseEntity<BibliothequeDto> responseEntityTest = testRestTemplate
					.getForEntity("/affichebibliotheque?id=" + bib.getId(), BibliothequeDto.class);
			//---------- Result------------------
			assertThat(responseEntityTest.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(responseEntityTest.getBody().getLivreDtos().size()).isEqualTo(3);
		}

		@Test
		@DisplayName("Api GET /affichebibliotheque ==> NE trouve PAS une biblio sur Id")
		void apiGetBibParId_notFound() {
			//---------- when ------------------

			//---------- Then ------------------
			ResponseEntity<String> responseEntity = testRestTemplate
					.getForEntity("/affichebibliotheque?id=1000", String.class);
			//---------- Result------------------
			assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(responseEntity.getBody()).isEqualTo("ERR_0001");
		}
	}
	@Nested
	@DisplayName("API DELETE /delete")
	class Test_Delete_Bib {
		@Test
		@DisplayName("Api DELETE /delete ==> NE trouve PAS une biblio sur Id")
		void deleteNotFound() {
			//---------- Then ------------------
			final ResponseEntity<String> responseEntity = testRestTemplate.exchange(
					"/delete?id=1000", HttpMethod.DELETE, null, String.class);
			//---------- Result------------------
			assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
			assertThat(responseEntity.getBody()).contains(ErrorCodes.BIBLIOTHEQUE_NOT_FOUND);
		}
		@Test
		@DisplayName("Api DELETE /delete ==> trouve la bib à deleter")
		void delete() {
			//---------- Given ------------------
			Bibliotheque bibliotheque = baseHelper.cree_bib_a_deleter();
			//---------- Then ------------------
			assertThat(bibliothequeJpaRepository.findById(bibliotheque.getId())).isNotEmpty();
			testRestTemplate.delete("/delete?id="+bibliotheque.getId());

			//---------- Result------------------
//			BibliothequeJpa bibliothequeJpa = bibliothequeJpaRepository.findById(bibliotheque.getId()).get();
			assertThat(bibliothequeJpaRepository.findById(bibliotheque.getId())).isEmpty();
		}
	}
	@Nested
	@DisplayName("API GET /toutesbibliotheque : récupère toutes les bibliothèques")
	class Test_GetAll {
		@Test
		@DisplayName("Api GET /toutesbibliotheque : pas de bib dans la base")
		void apiGetAllBib_Liste_vide() {
			//---------- Given ------------------
			baseHelper.erase_bib();
			//---------- Then ------------------
			ResponseEntity<BibliothequeDto[]> responseEntity = testRestTemplate.getForEntity("/toutesbibliotheque", BibliothequeDto[].class);
			//---------- Result------------------
			assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(responseEntity.getBody()).isEmpty();
		}
				@Test
		@DisplayName("Api GET /toutesbibliotheque")
		void apiGetAllBib() {
			//---------- Given ------------------
			baseHelper.erase_et_cree3bib();
			//---------- Then ------------------
			ResponseEntity<BibliothequeDto[]> responseEntity = testRestTemplate.getForEntity("/toutesbibliotheque", BibliothequeDto[].class);
			//---------- Result------------------
			assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(responseEntity.getBody()).hasSize(3);
		}
	}
	@Nested
	@DisplayName("API UPDATE /majbib")
	class Test_Update {
		@Test
		@DisplayName("Api UPDATE /majbib : màj d'une bib existante")
		void update() {
			//---------- Given ------------------
			List<BibliothequeDto> bibliothequeDtos = baseHelper.cree_et_lit_All_bibs();
			ResponseEntity<BibliothequeDto[]> responseEntityGetAll = testRestTemplate.getForEntity("/toutesbibliotheque",BibliothequeDto[].class);
			//---------- Then ------------------
			BibliothequeDto dto = bibliothequeDtos.get(2);
			dto.getDirecteurDto().setPrenom("Nouveau");
			dto.getLivreDtos().add(livreMapper.mapToDto(LIVRE_PHILO));
			testRestTemplate.put("/majbib",dto);

			ResponseEntity<BibliothequeDto> responseEntityTest = testRestTemplate
					.getForEntity("/affichebibliotheque?id=" + dto.getId(), BibliothequeDto.class);
			//---------- Result------------------
			assertThat(responseEntityTest.getBody().getLivreDtos()).hasSize(4);
			assertThat(responseEntityTest.getBody().getDirecteurDto().getPrenom()).isEqualTo("Nouveau");

		}
		@Test
		@DisplayName("Api UPDATE /majbib : màj d'une bib INEXISTANTE")
		void update_NOT_Found() {
			//---------- Then ------------------
			ResponseEntity<String> responseEntity =
					testRestTemplate.exchange("/majbib",HttpMethod.PUT,
							new HttpEntity<>(NOT_UPD_LIBRARY),String.class);
			//---------- Result------------------
			assertThat(responseEntity.getBody()).contains(ErrorCodes.BIBLIOTHEQUE_NOT_FOUND);
		}
	}
	@Test
	@DisplayName("BibliothequeMapper")
	void test_Bibliotheque_Mapper() {
		//---------- Given ------------------
		Bibliotheque bibliotheque= bibliothequeMapper.mapToEntity(bibliothequeMapper.mapToDto(NATIONAL_LIBRARY_MONTREUIL));
		System.out.println("bibliotheque="+bibliotheque.toString());
		System.out.println("NATIONAL_LIBRARY_MONTREUIL="+NATIONAL_LIBRARY_MONTREUIL.toString());
		//---------- Result------------------
		assertThat(bibliotheque.equalsValue(NATIONAL_LIBRARY_MONTREUIL)).isTrue();
		assertThat(bibliotheque).isEqualToComparingOnlyGivenFields(NATIONAL_LIBRARY_MONTREUIL,"type","adresse","directeur","livres");
	}
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
