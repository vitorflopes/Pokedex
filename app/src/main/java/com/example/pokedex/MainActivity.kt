package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pokedex.service.PokemonsServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val pokemonsService = retrofit.create(PokemonsServices::class.java)

        GlobalScope.launch {
            val pokemons = pokemonsService.getPokemon("chikorita")
            Log.d("PokeAPI", "${pokemons.name}")

            /*
            for (pokemon in pokemons.results!!) {
                Log.d("PokeAPI", "${pokemon.name}")
            }
             */
        }
    }
}