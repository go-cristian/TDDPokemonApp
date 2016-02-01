package com.example.iyubinest.pokemonapp.pokemon.list;

/**
 * Created by iyubinest on 1/29/16.
 */
public interface ListPokemonInteractor {
    public static int PAGE_SIZE = 20;

    void requestPage(int page, ListPokemonInteractorCallback listPokemonInteractorCallback);
}
