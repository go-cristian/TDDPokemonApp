package com.example.iyubinest.pokemonapp.pokemon.list.repository;

/**
 * Created by iyubinest on 1/30/16.
 */
public interface HttpRequestListener {
    void getPokemonString(String httpResult);

    void failure();
}
