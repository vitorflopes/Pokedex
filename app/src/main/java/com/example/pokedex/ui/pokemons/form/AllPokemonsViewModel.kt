package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.Pokemon
import com.example.pokedex.service.PokemonsServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AllPokemonsViewModel : ViewModel() {

    val pokList = mutableListOf<Pokemon>()
    var pokemonsList = MutableLiveData<MutableList<Pokemon>>()

    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val pokemonsService = retrofit.create(PokemonsServices::class.java)

        GlobalScope.launch {
            val pokemons = pokemonsService.getPokemons()
            for (pokemon in pokemons.results!!) {
                var pok = pokemonsService.getPokemon(pokemon.name!!)
                pokList.add(pok)
            }
        }
        pokemonsList.value = pokList
    }
}