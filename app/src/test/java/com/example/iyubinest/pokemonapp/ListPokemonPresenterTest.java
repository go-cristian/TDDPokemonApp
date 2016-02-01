package com.example.iyubinest.pokemonapp;

import com.example.iyubinest.pokemonapp.pokemon.Pokemon;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonInteractor;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonInteractorCallback;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonPresenter;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iyubinest on 1/29/16.
 */
public class ListPokemonPresenterTest {

    private ArrayList<Pokemon> allPokemons;
    private List<Pokemon> obtainedPokemons;
    private List<Pokemon> firstPagePokemons;
    private List<Pokemon> secondPagePokemons;
    private int pageRequested;
    private boolean loadingViewShow;
    private boolean errorShown;
    private boolean detailShow;

    @Before
    public void setup() {
        obtainedPokemons = new ArrayList<>();
        firstPagePokemons = getPokemons(0, ListPokemonInteractor.PAGE_SIZE);
        secondPagePokemons = getPokemons(ListPokemonInteractor.PAGE_SIZE, ListPokemonInteractor.PAGE_SIZE * 2);
    }

    @Test
    public void showLoadingAtStart() {
        loadingViewShow = false;
        new ListPokemonPresenter(new CollaboratorListPokemonView() {
            @Override
            public void showLoading() {
                loadingViewShow = true;
            }
        }, new CollaboratorListPokemonInteractor());
        assertEquals(true, loadingViewShow);
    }

    @Test
    public void loadAtStart() {
        new ListPokemonPresenter(new CollaboratorListPokemonView(), new ListPokemonInteractor() {
            @Override
            public void requestPage(int page, ListPokemonInteractorCallback listPokemonInteractorCallback) {
                pageRequested = page;
            }
        });
        assertEquals(0, pageRequested);
    }

    @Test
    public void showsErrorOnView() {
        new ListPokemonPresenter(new CollaboratorListPokemonView() {
            @Override
            public void showError() {
                errorShown = true;
            }
        }, new CollaboratorListPokemonInteractor() {
            @Override
            public void requestPage(int page, ListPokemonInteractorCallback listPokemonInteractorCallback) {
                listPokemonInteractorCallback.onFailure();
            }
        });
        assertEquals(true, errorShown);
    }

    @Test
    public void loadFirstPageShowsOnCollaboratorView() {
        new ListPokemonPresenter(new CollaboratorListPokemonView() {
            @Override
            public void render(List<Pokemon> pokemons) {
                obtainedPokemons = pokemons;
            }
        }, new CollaboratorListPokemonInteractor() {
            @Override
            public void requestPage(int page, ListPokemonInteractorCallback listPokemonInteractorCallback) {
                listPokemonInteractorCallback.onSuccess(firstPagePokemons);
            }
        });
        assertEquals(firstPagePokemons, obtainedPokemons);
    }

    @Test
    public void loadSecondPageOnScrolls() {
        allPokemons = new ArrayList<>();
        allPokemons.addAll(firstPagePokemons);
        allPokemons.addAll(secondPagePokemons);
        ListPokemonPresenter listPokemonPresenter = new ListPokemonPresenter(
                new CollaboratorListPokemonView() {
                    @Override
                    public void render(List<Pokemon> pokemons) {
                        obtainedPokemons.addAll(pokemons);
                    }
                }, new CollaboratorListPokemonInteractor() {
            @Override
            public void requestPage(int page, ListPokemonInteractorCallback listPokemonInteractorCallback) {
                if (page == 0)
                    listPokemonInteractorCallback.onSuccess(firstPagePokemons);
                if (page == 1)
                    listPokemonInteractorCallback.onSuccess(secondPagePokemons);
            }
        });
        listPokemonPresenter.endOfPageReached();
        assertEquals(allPokemons, obtainedPokemons);
    }

    @Test
    public void testShowDetail() {
        ListPokemonPresenter listPokemonPresenter = new ListPokemonPresenter(new CollaboratorListPokemonView() {
            @Override
            public void showDetail() {
                detailShow = true;
            }
        }, new CollaboratorListPokemonInteractor());
        listPokemonPresenter.showDetail();
        assertEquals(true, detailShow);
    }

    private List<Pokemon> getPokemons(int start, int pageSize) {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        for (int i = start; i < pageSize; i++) pokemons.add(new Pokemon(i, "", 0));
        return pokemons;
    }

    private class CollaboratorListPokemonView implements ListPokemonView {
        @Override
        public void showLoading() {
        }

        @Override
        public void showError() {
        }

        @Override
        public void render(List<Pokemon> pokemons) {
        }

        @Override
        public void showDetail() {
        }
    }

    private class CollaboratorListPokemonInteractor implements ListPokemonInteractor {

        @Override
        public void requestPage(int page, ListPokemonInteractorCallback listPokemonInteractorCallback) {

        }
    }
}
