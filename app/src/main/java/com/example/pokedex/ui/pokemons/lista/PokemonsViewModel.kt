package com.example.pokedex.ui.pokemons.lista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.apiModel.Pokemon

class PokemonsViewModel : ViewModel() {

    var pokemons = MutableLiveData<List<Pokemon>>()
    var msg = MutableLiveData<String>()

    init {
        val idCampeao = AuthDao.getCurrentUser()!!.uid

        PokemonDao.listarPokemonsCampeao(idCampeao)
            .addSnapshotListener { snapshot, error ->
            if (error != null) {
                msg.value = error.message
            }
            if (snapshot != null) {
                pokemons.value = snapshot.toObjects(Pokemon::class.java)
            }
        }
    }
}