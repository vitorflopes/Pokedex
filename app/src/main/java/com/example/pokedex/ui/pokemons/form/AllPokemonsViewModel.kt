package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.apiModel.Pokemon
import com.example.pokedex.service.RetroFit
import kotlinx.coroutines.launch

class AllPokemonsViewModel : ViewModel() {

    var pokemonsList = MutableLiveData<MutableList<Pokemon>>()

    init {
        val pokList = mutableListOf<Pokemon>()

        viewModelScope.launch {
            val pokemons = RetroFit.pokemonsService.getPokemons()
            for (pokemon in pokemons.results!!) {
                val pok = RetroFit.pokemonsService.getPokemon(pokemon.name)
                pokList.add(pok)
            }
            pokemonsList.value = pokList
        }
    }
}