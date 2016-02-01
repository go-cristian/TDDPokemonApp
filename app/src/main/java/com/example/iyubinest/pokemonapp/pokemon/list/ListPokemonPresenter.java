package com.example.iyubinest.pokemonapp.pokemon.list;

import com.example.iyubinest.pokemonapp.pokemon.Pokemon;

import java.util.List;

/**
 * Created by iyubinest on 1/29/16.
 */
public class ListPokemonPresenter {

    private final ListPokemonInteractorCallback listPokemonInteractorCallback = new ListPokemonInteractorCallback() {
        @Override
        public void onSuccess(List<Pokemon> pokemons) {
            view.render(pokemons);
        }

        @Override
        public void onFailure() {
            view.showError();
        }
    };
    private final ListPokemonView view;
    private final ListPokemonInteractor interactor;
    private int currentPage = 0;

    public ListPokemonPresenter(ListPokemonView view, ListPokemonInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        view.showLoading();
        interactor.requestPage(currentPage, listPokemonInteractorCallback);
    }

    public void endOfPageReached() {
        interactor.requestPage(++currentPage, listPokemonInteractorCallback);
    }

    public void showDetail() {
        view.showDetail();
    }
}
