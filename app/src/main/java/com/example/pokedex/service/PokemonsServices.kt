package com.example.pokedex.service

import com.example.pokedex.model.PokemonsResponse
import com.example.pokedex.model.apiModel.*
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsServices {

    @GET("pokemon?limit=30")
    suspend fun getPokemons() : PokemonsResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String) : Pokemon

    @GET("pokemon-species/{id}/")
    suspend fun getSpecies(@Path("id") id: String) : PokemonSpecies

    @GET("ability/{name}/")
    suspend fun getAbility(@Path("name") name: String) : Ability

    @GET("pokemon-form/{name}/")
    suspend fun getForm(@Path("name") name: String) : PokemonForm

    @GET("move/{name}/")
    suspend fun getMove(@Path("name") name: String) : Move

    @GET("stat/{name}/")
    suspend fun getStat(@Path("name") name: String) : Stat

    @GET("type/{name}/")
    suspend fun getType(@Path("name") name: String) : Type

    @GET("item/{name}/")
    suspend fun getItem (@Path("name") name: String) : Item

    @GET("nature")
    suspend fun getNatures () : PokemonNature

    @GET("nature/{name}/")
    suspend fun getNature(@Path("name") name: String) : Nature

    @GET("item?limit=1610")
    suspend fun getItens() : PokemonItem

    @GET("version-group?limit=30")
    suspend fun getGames() : PokemonGame
}