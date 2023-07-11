package br.com.curso.udemy.productapi.adapters.out.persistence.exceptions;

import lombok.Data;

@Data
public class ExceptionDetails {
    private int status;
    private String message;
}
