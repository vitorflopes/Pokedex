package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.apiModel.Pokemon
import com.example.pokedex.service.PokemonsServices
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonFormViewModel() : ViewModel() {

    var status = MutableLiveData<Boolean>()
    var msg = MutableLiveData<String>()
    var pokemon = MutableLiveData<Pokemon>()

    init {

    }

    fun retornaPokemon(pokemonName: String) {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val pokemonsService = retrofit.create(PokemonsServices::class.java)
        viewModelScope.launch {
            pokemon.value = pokemonsService.getPokemon(pokemonName)
        }
    }

    fun salvar(pokemon: Pokemon) {

        val idCampeao = AuthDao.getCurrentUser()!!.uid
        pokemon.campeaoId = idCampeao
        val task = PokemonDao.inserir(pokemon, idCampeao)

        task.addOnSuccessListener {
            msg.value = "Pokemon criado com sucesso."
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }
    }
}