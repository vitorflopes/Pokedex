package com.example.pokedex.model

class Pokemon (
    //val nomePokemon: String? = null,
    val name: String? = null,
    val sprites: Sprite? = null,

    val categoria: String? = null,
    val hp: Int? = 0,
    val ataque: Int? = 0,
    val defesa: Int? = 0,
    var campeaoId: String? = null,
    /*
    // Novos atributos
    val pokemonName: String? = null,
    val species: String? = null,
    val height: Float? = null,
    val weight: Float? = null,
    val abilities: List<String>? = null,
    val abilitiesHidden: String? = null,
    val eggGroup: String? = null,
    val hatchTime: Int? = null
    */
) {







    fun strNomePokemon() : String {
        return "$name"
    }

    fun strCategoria() : String {
        return "$categoria"
    }

    fun strHp() : String {
        return "${hp.toString()}"
    }

    fun strAtaque() : String {
        return "${ataque.toString()}"
    }

    fun strDefesa() : String {
        return "${defesa.toString()}"
    }
}