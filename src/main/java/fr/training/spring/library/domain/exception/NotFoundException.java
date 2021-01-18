package fr.training.spring.library.domain.exception;

public class NotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
}
