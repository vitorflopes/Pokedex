package com.example.pokedex.ui.editar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.dao.CampeaoDao
import com.example.pokedex.model.Campeao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
class EditarViewModel (val campeaoDao: CampeaoDao) : ViewModel() {

    val status = MutableLiveData<Boolean>()
    var campeao = MutableLiveData<Campeao>()

    init {
        status.value = false
    }

    fun detalhesCampeao (idCampeao : Long) {
        viewModelScope.launch(Dispatchers.Default) {
            campeao.postValue(campeaoDao.exibir(idCampeao))
        }
    }

    fun editarCampeao (nomeCampeao: String, email: String, senha: String, idCampeao: Long,)  {

        val campeao = Campeao(nomeCampeao, email, senha, idCampeao)

        viewModelScope.launch(Dispatchers.Default) {
            campeaoDao.atualizar(campeao)
            status.postValue(true)
        }
    }
}*/