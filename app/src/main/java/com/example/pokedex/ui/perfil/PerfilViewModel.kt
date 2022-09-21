package com.example.pokedex.ui.perfil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.CampeaoDao
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.Campeao

class PerfilViewModel : ViewModel() {

    val msg = MutableLiveData<String>()
    var campeao = MutableLiveData<Campeao>()
    val numPokemonsUser = MutableLiveData<Int>()

    fun detalhesCampeao () {

        val idCampeao = AuthDao.getCurrentUser()!!.uid

        CampeaoDao.exibirCampeao(idCampeao).addOnSuccessListener {
            campeao.value = it.toObjects(Campeao::class.java).first()
        }.addOnFailureListener {
                msg.value = it.message
        }
    }

    fun numPokemons() {
        val idUsuario = AuthDao.getCurrentUser()!!.uid

        PokemonDao.pokemonsPorId(idUsuario).addOnSuccessListener {
            numPokemonsUser.value = it.size()
        }.addOnFailureListener {
            msg.value = it.message
        }
    }
}