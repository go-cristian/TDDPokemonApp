package com.example.iyubinest.pokemonapp.pokemon.list.repository;

import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonInteractor;
import com.example.iyubinest.pokemonapp.pokemon.list.ListPokemonInteractorCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by iyubinest on 1/30/16.
 */
public class DefaultHttpRequest implements HttpRequest {
    @Override
    public void getPokemons(int page, HttpRequestListener httpRequestListener) {
        try {
            int limit = ListPokemonInteractor.PAGE_SIZE;
            URL u = new URL("http://pokeapi.co/api/v1/pokemon/?limit=" + limit + "&offset=0");
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    httpRequestListener.getPokemonString(sb.toString());
                default:
                    httpRequestListener.failure();
            }
        } catch (IOException e) {
            httpRequestListener.failure();
        }
    }

    /**
     * Created by iyubinest on 1/30/16.
     */
    public static class HttpListPokemonInteractor implements ListPokemonInteractor {
        private final HttpRequest httpRequest;
        private ListPokemonInteractorCallback listPokemonInteractorCallback;

        public HttpListPokemonInteractor(HttpRequest httpRequest) {
            this.httpRequest = httpRequest;
        }

        @Override
        public void requestPage(int page, ListPokemonInteractorCallback callback) {
            listPokemonInteractorCallback = callback;
            httpRequest.getPokemons(page, new HttpRequestListener() {
                @Override
                public void getPokemonString(String httpResult) {
                    try {
                        listPokemonInteractorCallback.onSuccess(PokemonParser.parse(httpResult));
                    } catch (PokemonParser.PokemonParseException e) {
                        listPokemonInteractorCallback.onFailure();
                    }
                }

                @Override
                public void failure() {
                    listPokemonInteractorCallback.onFailure();
                }
            });
        }


    }
}
