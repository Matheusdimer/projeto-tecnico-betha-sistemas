package com.betha.manutencao.services.exceptions;

/**
 * Exception para Integridade Referencial de Dados
 */
public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
