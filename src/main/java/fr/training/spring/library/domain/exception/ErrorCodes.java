package fr.training.spring.library.domain.exception;

public final class ErrorCodes {
    private ErrorCodes() {

    }
    public static final String BIBLIOTHEQUE_NOT_FOUND = "ERR_0001";
//    public static final String BIBLIOTHEQUE_NON_PRESENTE = "ERR_0002";
    public static final String BOOK_NOT_FOUND = "ERR_0003";
    public static final String DIRECTEUR_MUST_BE_PRESENT = "ERR_0004";
    public static final String DIRECTEUR_NAME_HAVE_MORE_THAN_ONE_CARACTER = "ERR_0005";
    public static final String ADRESSE_MUST_BE_PRESENT = "ERR_0006";
    public static final String ONE_LIVRE_OR_MORE = "ERR_0007";
    public static final String DIRECTEUR_NOM_PRENOM_NOT_VIDE = "ERR_0008";
    public static final String AUTEUR_NOT_FOUND = "ERR_0009";


}
