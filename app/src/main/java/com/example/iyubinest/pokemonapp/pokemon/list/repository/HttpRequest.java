package com.example.iyubinest.pokemonapp.pokemon.list.repository;

/**
 * Created by iyubinest on 1/30/16.
 */
public interface HttpRequest {
    void getPokemons(int page, HttpRequestListener httpRequestListener);
}
