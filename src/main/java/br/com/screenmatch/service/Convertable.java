package br.com.screenmatch.service;

public interface Convertable {
    <T> T obterDados(String json, Class<T> classe);
}
