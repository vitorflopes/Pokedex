package com.example.pokedex.service

import com.example.pokedex.model.PokemonsResponse
import com.example.pokedex.model.apiModel.*
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsServices {

    @GET("pokemon?limit=100")
    suspend fun getPokemons() : PokemonsResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String) : Pokemon

    @GET("pokemon-species/{id}/")
    suspend fun getSpecies(@Path("id") id: String) : PokemonSpecies

    @GET("ability/{name}/")
    suspend fun getAbility(@Path("name") id: String) : Ability

    @GET("pokemon-form/{name}/")
    suspend fun getForm(@Path("name") id: String) : PokemonForm

    @GET("move/{name}/")
    suspend fun getMove(@Path("name") id: String) : Move
}