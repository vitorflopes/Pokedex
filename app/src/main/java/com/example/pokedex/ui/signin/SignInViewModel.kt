package com.example.pokedex.ui.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.AuthDao

class SignInViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    init {
        status.value = false

        /*
        viewModelScope.launch {
            val pokeApi = PokeApiClient()
            val bulbasaur = pokeApi.getPokemon(1)
            println(bulbasaur)
        }
         */
    }

    fun autenticar (email: String, senha: String) {

        val task = AuthDao.validarCampeao(email, senha)

        task.addOnSuccessListener {
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }

        /*
        viewModelScope.launch (Dispatchers.Default) {
            try {
                val campeao = campeaoDao.autenticar(email, senha)

                if (campeao != null) {
                    campeaoId.postValue(campeao.id)
                    status.postValue(true)
                }
                else {
                    status.postValue(false)
                    msg.postValue("Campeão inválido.")
                }
            } catch (err: Exception) {
                status.postValue(false)
                msg.postValue("Campeão inválido.")
            }
        }
        */
    }
}