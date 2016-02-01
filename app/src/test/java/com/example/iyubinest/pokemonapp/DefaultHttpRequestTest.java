package com.example.iyubinest.pokemonapp;

import com.example.iyubinest.pokemonapp.pokemon.list.repository.DefaultHttpRequest;
import com.example.iyubinest.pokemonapp.pokemon.list.repository.HttpRequest;
import com.example.iyubinest.pokemonapp.pokemon.list.repository.HttpRequestListener;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Created by iyubinest on 1/30/16.
 */
public class DefaultHttpRequestTest {

    private String result;

    @Test
    public void testGet() throws FileNotFoundException {
        HttpRequest defaultHttpRequest = new DefaultHttpRequest();
        defaultHttpRequest.getPokemons(0, new HttpRequestListener() {
            @Override
            public void getPokemonString(String httpResult) {
                result = httpResult;
            }

            @Override
            public void failure() {

            }
        });
        Assert.assertEquals(cleanString(TestJSON.getJsonPage()), cleanString(result));
    }

    private String cleanString(String s) {
        return s.replaceAll("\n", "").replaceAll("\r", "").replaceAll(" ", "").trim();
    }
}
