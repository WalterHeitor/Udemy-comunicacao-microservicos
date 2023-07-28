package br.com.curso.udemy.productapi.aplication.core.logging;

public interface Logger {
    void info(String message);
    void warn(String message);
    void error(String message);
    void error(String message, Throwable throwable);
}
