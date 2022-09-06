package com.example.pokedex.service

import com.example.pokedex.model.PokemonsResponse
import com.example.pokedex.model.apiModel.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsServices {

    @GET("pokemon?limit=1200")
    suspend fun getPokemons() : PokemonsResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String) : Pokemon
}