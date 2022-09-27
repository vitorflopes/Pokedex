package com.example.pokedex.ui.pokemons.detalhes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.CreatedPokemon

class DetalhesPokemonViewModel : ViewModel() {

    val pokemon = MutableLiveData<CreatedPokemon>()
    val msg = MutableLiveData<String>()

    fun retornaPokemon(pokemonId: Int) {
        PokemonDao.retornaPokemon(pokemonId).addOnSuccessListener {
            pokemon.value = it.toObjects(CreatedPokemon::class.java).first()
        }.addOnFailureListener {
            msg.value = it.message
        }
    }

    fun excluirPokemon() {
        PokemonDao.deletaPokemon(pokemon.value!!.idFirebase!!)
    }
}