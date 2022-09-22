package com.example.pokedex.ui.pokemons.userPokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.CreatedPokemon

class UserPokemonsViewModel : ViewModel() {

    var pokemonsList = MutableLiveData<MutableList<CreatedPokemon>>()
    val msg = MutableLiveData<String>()

    fun retornaPokemonsUser() {
        val idUsuario = AuthDao.getCurrentUser()!!.uid

        PokemonDao.pokemonsPorId(idUsuario).addOnSuccessListener {
            pokemonsList.value = it.toObjects(CreatedPokemon::class.java)
        }.addOnFailureListener {
            msg.value = it.message
        }
    }
}