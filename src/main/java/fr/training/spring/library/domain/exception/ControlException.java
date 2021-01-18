package fr.training.spring.library.domain.exception;

public class ControlException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ControlException(String message, String errorCode) {
        super(message, errorCode);
    }
}
