package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.apiModel.Pokemon

class PokemonFormViewModel : ViewModel() {

    var status = MutableLiveData<Boolean>()
    var msg = MutableLiveData<String>()

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