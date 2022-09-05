package com.example.pokedex.dao

import com.example.pokedex.model.Pokemon
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PokemonDao {

    companion object {

        fun listarPokemonsCampeao(idCampeao: String): CollectionReference {
            return Firebase.firestore.collection("Campeoes")
                .document(idCampeao).collection("Pokemons")
        }

        fun inserir(pokemon: Pokemon, idCampeao: String): Task<DocumentReference> {
            return Firebase.firestore.collection("Campeoes")
                .document(idCampeao).collection("Pokemons").add(pokemon)
        }
    }
}