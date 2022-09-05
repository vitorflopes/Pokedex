package com.example.pokedex.service

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsServices {

    @GET("pokemon?limit=1200")
    suspend fun getPokemons() : PokemonsResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String) : Pokemon
}