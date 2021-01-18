package fr.training.spring.library.exposition;

import fr.training.spring.library.application.ServiceBibliotheque;
import fr.training.spring.library.domain.enumeration.Type;
import fr.training.spring.library.exposition.entitesDto.BibliothequeDto;
import fr.training.spring.library.exposition.entitesDto.BibliothequeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@Validated
public class ControllerBibliotheque {
   @Autowired
   private ServiceBibliotheque serviceBibliotheque;
   @Autowired
   private BibliothequeMapper bibliothequeMapper;

    @PostMapping(value = {"/create"},consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponse(code = 409, message = "Conflict", response = ErrorModel.class)
//    public BibliothequeDto create(@RequestBody  BibliothequeDto bibliothequeDto) {
    public BibliothequeDto create(@Valid @RequestBody  BibliothequeDto bibliothequeDto) {
        return bibliothequeMapper.mapToDto(serviceBibliotheque.create(bibliothequeMapper.mapToEntity(bibliothequeDto)));
    }

    @DeleteMapping(value = {"/delete"})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@RequestParam final Long id) {
        serviceBibliotheque.delete(id);
        return "delete OK";
    }

    @PutMapping(value = {"/majbib"})
    @ResponseStatus(HttpStatus.OK)
    public BibliothequeDto majBib(@RequestBody BibliothequeDto bibliothequeDto) {
        return bibliothequeMapper.mapToDto(serviceBibliotheque.update(bibliothequeMapper.mapToEntity(bibliothequeDto)));
    }
    @GetMapping(value = {"/affichebibliotheque"},produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BibliothequeDto afficheBibliotheque(@RequestParam final Long id) {
        return bibliothequeMapper.mapToDto(serviceBibliotheque.findById(id));
    }

    @GetMapping(value = {"/toutesbibliotheque"},produces = {"application/json"})
    public List<BibliothequeDto> toutesBibliotheque() {
        return bibliothequeMapper.mapToDtoList(serviceBibliotheque.findAll());
    }

    @GetMapping(value = {"/toutespartype/{type}"},produces = {"application/json"})
    public List<BibliothequeDto> toutesParType(@PathVariable("type") Type type) {
        return bibliothequeMapper.mapToDtoList(serviceBibliotheque.findAllByType(type));
    }

    @GetMapping(value = {"/toutesparprenom/{prenom}"},produces = {"application/json"})
    public List<BibliothequeDto> toutesParPrenom(@PathVariable("prenom") String prenom) {
        return bibliothequeMapper.mapToDtoList(serviceBibliotheque.finaAllByDirecteurSurname(prenom));
    }

    @GetMapping(value = {"/toutesparcodepostal/{codePostal}"},produces = {"application/json"})
    public List<BibliothequeDto> toutesParCodePostal(@PathVariable("codePostal") String codePostal) {
        return bibliothequeMapper.mapToDtoList(serviceBibliotheque.finaAllByCodePostal(codePostal));
    }
}
