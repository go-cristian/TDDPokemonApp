package com.example.iyubinest.pokemonapp.pokemon.list;

import com.example.iyubinest.pokemonapp.pokemon.Pokemon;

import java.util.List;

/**
 * Created by iyubinest on 1/29/16.
 */
public interface ListPokemonView {
    void showLoading();

    void showError();

    void render(List<Pokemon> pokemons);

    void showDetail();
}
