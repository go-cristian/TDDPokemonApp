package com.example.iyubinest.pokemonapp.pokemon.list.repository;

import com.example.iyubinest.pokemonapp.pokemon.Pokemon;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iyubinest on 1/30/16.
 */
public class PokemonParser {
    public static List<Pokemon> parse(String pokemons) throws PokemonParseException {
        Gson gson = new Gson();
        return parser(gson.fromJson(pokemons, PokemonGsonModel.class));
    }

    private static List<Pokemon> parser(PokemonGsonModel pokemonGsonModel) throws PokemonParseException {
        try {
            List<Pokemon> pokemons = new ArrayList<>(pokemonGsonModel.objects.size());
            for (PokemonGsonModel.PokemonModel object : pokemonGsonModel.objects)
                pokemons.add(parser(object));
            return pokemons;
        } catch (Exception e) {
            throw new PokemonParseException();
        }
    }

    private static Pokemon parser(PokemonGsonModel.PokemonModel model) {
        return new Pokemon(model.pkdx_id, model.name, model.attack);
    }

    public static class PokemonParseException extends Throwable {
    }
}
