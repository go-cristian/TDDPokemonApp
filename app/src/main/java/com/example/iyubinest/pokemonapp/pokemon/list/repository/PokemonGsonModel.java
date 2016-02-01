package com.example.iyubinest.pokemonapp.pokemon.list.repository;

import java.util.List;

/**
 * Created by iyubinest on 1/30/16.
 */
public class PokemonGsonModel {
    List<PokemonModel> objects;

    public class PokemonModel {
        int attack;
        String name;
        int pkdx_id;
    }
}
