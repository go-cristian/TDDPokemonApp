package com.example.iyubinest.pokemonapp.pokemon.list;

import com.example.iyubinest.pokemonapp.pokemon.Pokemon;

import java.util.List;

/**
 * Created by iyubinest on 1/30/16.
 */
public interface ListPokemonInteractorCallback {
    void onSuccess(List<Pokemon> pokemons);

    void onFailure();
}
