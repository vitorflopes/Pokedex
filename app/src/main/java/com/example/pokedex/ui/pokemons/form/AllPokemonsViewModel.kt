package com.example.pokedex.ui.pokemons.form

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.apiModel.Pokemon
import com.example.pokedex.service.RetroFit
import kotlinx.coroutines.launch

class AllPokemonsViewModel(context: Context) : ViewModel() {

    var pokemonsList = MutableLiveData<MutableList<Pokemon>>()
    var offset = 0
    var offsetObs = MutableLiveData(0)

    init {
        carregaListPokemons(context)
    }

    private fun carregaListPokemons(context: Context, offset: Int = 0) {
        val pokList = mutableListOf<Pokemon>()

        viewModelScope.launch {
            val pokemons = RetroFit.pokemonsService(context).getPokemons(offset = offset)
            for (pokemon in pokemons.results!!) {
                val pok = RetroFit.pokemonsService(context).getPokemon(pokemon.name)
                pokList.add(pok)
            }
            pokemonsList.value = pokList
            offsetObs.value = offset
        }
    }

    fun retornaOffsetAdd(context: Context) {
        offset += 30
        carregaListPokemons(context, offset)
    }

    fun retornaOffsetMin(context: Context) {
        offset -= 30
        carregaListPokemons(context, offset)
    }
}