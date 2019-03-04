package nthu.learncloud.exception;

import org.springframework.validation.Errors;

public class InvaildRequestException extends RuntimeException{
    private Errors erros;

    public InvaildRequestException(String message, Errors erros) {
        super(message);
        this.erros = erros;
    }

    public Errors getErros() {
        return erros;
    }
}
