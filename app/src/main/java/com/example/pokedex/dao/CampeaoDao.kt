package com.example.pokedex.dao

import com.example.pokedex.model.Campeao
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CampeaoDao {

    companion object {

        private val collection = Firebase.firestore.collection("Campeoes")

        fun salvarCampeao(idCampeao: String, campeao: Campeao) {
            collection.document(idCampeao).set(campeao)
        }

        fun exibirCampeao(idCampeao: String): Query {
            return collection.whereEqualTo("id", idCampeao)
        }

        fun listarCampeoes(): CollectionReference {
            return collection
        }
    }

    /*
    @Query ("SELECT * FROM Campeao")
    fun listar() : List<Campeao>

    @Insert
    fun inserir(campeao: Campeao) : Long

    @Query ("SELECT * FROM Campeao WHERE id = :id")
    fun exibir(id: Long) : Campeao

    @Query ("SELECT * FROM Campeao WHERE email = :email AND senha = :senha")
    fun autenticar(email: String, senha: String) : Campeao

    @Update
    fun atualizar(campeao: Campeao)

    @Delete
    fun excluir(campeao: Campeao)
    */
}