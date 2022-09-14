package com.example.pokedex.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFit {
    companion object {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val pokemonsService = retrofit.create(PokemonsServices::class.java)
    }
}