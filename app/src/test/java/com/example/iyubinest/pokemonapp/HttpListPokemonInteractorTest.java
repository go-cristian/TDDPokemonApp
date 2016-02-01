package com.example.iyubinest.pokemonapp;

import com.example.iyubinest.pokemonapp.pokemon.Pokemon;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonInteractor;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonInteractorCallback;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonPresenter;
import com.example.iyubinest.pokemonapp.pokemon.list.repository.DefaultHttpRequest;
import com.example.iyubinest.pokemonapp.pokemon.list.repository.HttpRequest;
import com.example.iyubinest.pokemonapp.pokemon.list.repository.HttpRequestListener;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iyubinest on 1/30/16.
 */
public class HttpListPokemonInteractorTest {

    private List<Pokemon> requestedPokemons;
    private boolean errorThrown;

    @Test
    public void getPokemonPage() {
        ListPokemonInteractor listPokemonInteractor = new DefaultHttpRequest.HttpListPokemonInteractor(new HttpTestRequest());
        ListPokemonInteractorCallback callback = new ListPokemonInteractorCallback() {
            @Override
            public void onSuccess(List<Pokemon> pokemons) {
                requestedPokemons = pokemons;
            }

            @Override
            public void onFailure() {

            }
        };
        listPokemonInteractor.requestPage(0, callback);
        assertNotNull(requestedPokemons);
        assertEquals(ListPokemonInteractor.PAGE_SIZE, requestedPokemons.size());
        for (Pokemon pokemon : requestedPokemons) assertNotNull(pokemon);
    }

    @Test
    public void sendError() {
        errorThrown = false;
        ListPokemonInteractor listPokemonInteractor = new DefaultHttpRequest.HttpListPokemonInteractor(new HttpErrorTestRequest());
        ListPokemonInteractorCallback callback = new ListPokemonInteractorCallback() {
            @Override
            public void onSuccess(List<Pokemon> pokemons) {

            }

            @Override
            public void onFailure() {
                errorThrown = true;
            }
        };
        listPokemonInteractor.requestPage(0, callback);
        assertTrue(errorThrown);
    }

    private class HttpTestRequest implements HttpRequest {
        @Override
        public void getPokemons(int page, HttpRequestListener httpRequestListener) {
            try {
                httpRequestListener.getPokemonString(TestJSON.getJsonPage());
            } catch (FileNotFoundException ignored) {
            }
        }
    }

    private class HttpErrorTestRequest implements HttpRequest {
        @Override
        public void getPokemons(int page, HttpRequestListener httpRequestListener) {
            httpRequestListener.getPokemonString(null);
        }
    }
}
