package com.example.pokedex.ui.perfil

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.CampeaoDao
import com.example.pokedex.model.Campeao

class PerfilViewModel : ViewModel() {

    var campeao = MutableLiveData<Campeao>()

    fun detalhesCampeao () {

        val idCampeao = AuthDao.getCurrentUser()!!.uid

        CampeaoDao.exibirCampeao(idCampeao).addSnapshotListener { snapshot, error ->
            if (error != null) {

            }
            if (snapshot != null) {
                campeao.value = snapshot.toObjects(Campeao::class.java).first()
            }
        }
    }

    /*
    fun excluirCampeao (campeao: Campeao) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                campeaoDao.excluir(campeao)
            } catch (err: Exception) {
                Log.d("ExcluirCampeao", "${err.message}")
            }
        }
    }
     */
}