package br.com.facec.programacao2.exceptions;

public class RegistroNaoExistenteException extends RuntimeException {

    public RegistroNaoExistenteException(String message) {
        super(message);
    }
}
