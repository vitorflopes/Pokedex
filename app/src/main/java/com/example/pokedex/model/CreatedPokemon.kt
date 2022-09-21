package com.example.pokedex.model

class CreatedPokemon(

    val sprite: String?,
    val nickname: String?,
    val species: String,
    val lvl: String,
    val gender: Boolean,
    val ability: String,
    val item: String?,
    val iv: List<String>?,
    val ev: List<String>?,
    val nature: String?,
    val moves: List<String>,
    //var id: Long = 0,
    val idUsuario: String? = null

) {

    override fun toString(): String {
        val name: String
        val ivs: String
        val evs: String

        if(nickname.isNullOrBlank()){
            name = "$species"
        }else{
            name = "$nickname ($species)"
        }

        if(ev.isNullOrEmpty()){
            evs = ""
        }else{
            evs = "EVs: ${ev[0]} HP / ${ev[1]} Atk / ${ev[2]} Def /" +
                    " ${ev[3]} SpA / ${ev[4]} SpD / ${ev[5]} Spe"
        }

        if(iv.isNullOrEmpty()){
            ivs = ""
        }else{
            ivs = "IVs: ${iv[0]} HP / ${iv[1]} Atk / ${iv[2]} Def /" +
                    " ${iv[3]} SpA / ${iv[4]} SpD / ${iv[5]} Spe"
        }

        val pkmn = "$name @ $item\n" +
                "Ability: $ability\n" +
                "$evs\n" +
                "$ivs\n" +
                "$nature Nature\n" +
                "- ${moves[0]}\n" +
                "- ${moves[1]}\n" +
                "- ${moves[2]}\n" +
                "- ${moves[3]}"

        return pkmn
    }
}