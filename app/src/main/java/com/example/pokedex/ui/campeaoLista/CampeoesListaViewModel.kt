package com.example.pokedex.ui.campeaoLista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.CampeaoDao
import com.example.pokedex.model.Campeao

class CampeoesListaViewModel : ViewModel() {

    var campeoes = MutableLiveData<List<Campeao>>()
    var msg = MutableLiveData<String>()

    init {

        CampeaoDao.listarCampeoes().addSnapshotListener { snapshot, error ->
            if (snapshot != null) {
                campeoes.value = snapshot.toObjects(Campeao::class.java)
            }
            if (error != null) {
                msg.value = error.message
            }
        }

        /*
        viewModelScope.launch(Dispatchers.Default) {
            val campeoesDaBase = campeaoDao.listar()
            campeoes.postValue(campeoesDaBase)
        }
        */
    }

    /*
    fun excluir (campeao: Campeao) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                campeaoDao.excluir(campeao)
                status.postValue(true)
            } catch (err: Exception) {
                Log.d("ExcluirCampeao", "${err.message}")
                msg.postValue(err.message)
            }
        }
    }
    */
}