package br.com.facec.programacao2.exceptions;

public class CampoObrigatorioException extends RuntimeException {

    public CampoObrigatorioException(String message) {
        super(message);
    }
}
