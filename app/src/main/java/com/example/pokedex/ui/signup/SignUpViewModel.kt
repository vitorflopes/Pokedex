package com.example.pokedex.ui.signup

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.CampeaoDao
import com.example.pokedex.model.Campeao
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class SignUpViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()
    var foto: Bitmap? = null

    init {
        status.value = false
    }

    fun pegarFoto(foto: Bitmap) {
        this.foto = foto
    }

    fun salvarCampeao(
        nomeCampeao: String, email: String, senha: String) {

        val task = AuthDao.cadastrarCampeao(email, senha)

        task.addOnSuccessListener {
            status.value = true
            val idCampeao = AuthDao.getCurrentUser()!!.uid
            val campeao = Campeao(idCampeao, nomeCampeao, email)
            CampeaoDao.salvarCampeao(idCampeao, campeao)
        }.addOnFailureListener {
            msg.value = it.message
        }


        /*
        viewModelScope
            .launch(Dispatchers.Default) {
            val campeaoId = campeaoDao.inserir(campeao)
            uploadFoto(campeaoId)
            status.postValue(true)
        }
        */
    }

    fun uploadFoto(id: Long) {
        val reference = Firebase.storage.reference.child("foto${id}.jpeg")

        val baos = ByteArrayOutputStream()
        this.foto!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var task = reference.putBytes(baos.toByteArray())
        task.addOnSuccessListener { msg.value = "Campeão inserido com sucesso." }
        task.addOnFailureListener { msg.value = it.message }
    }
}