package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.dao.PokemonDao
import com.example.pokedex.model.apiModel.*
import com.example.pokedex.service.RetroFit
import kotlinx.coroutines.launch

class PokemonFormViewModel() : ViewModel() {

    var status = MutableLiveData<Boolean>()
    var msg = MutableLiveData<String>()

    var pokemon = MutableLiveData<Pokemon>()
    var species = MutableLiveData<PokemonSpecies>()
    var listAbilities = MutableLiveData<List<Ability>>()
    var listForms = MutableLiveData<List<PokemonForm>>()
    var listMoves = MutableLiveData<List<Move>>()

    fun retornaPokemon(pokemonName: String) {

        viewModelScope.launch {
            val helpListAbility = arrayListOf<Ability>()
            val helpListForm = arrayListOf<PokemonForm>()
            val helpListMove = arrayListOf<Move>()

            pokemon.value = RetroFit.pokemonsService.getPokemon(pokemonName)
            species.value = RetroFit.pokemonsService.getSpecies(pokemon.value!!.id.toString())

            for (abilitie in pokemon.value!!.abilities) {
                val ablt = RetroFit.pokemonsService.getAbility(abilitie.ability.name)
                helpListAbility.add(ablt)
            }
            listAbilities.value = helpListAbility

            for (form in pokemon.value!!.forms) {
                val frm = RetroFit.pokemonsService.getForm(form.name)
                helpListForm.add(frm)
            }
            listForms.value = helpListForm

            for (move in pokemon.value!!.moves) {
                val mv = RetroFit.pokemonsService.getMove(move.move.name)
                helpListMove.add(mv)
            }
            listMoves.value = helpListMove
        }
    }

    fun salvar(pokemon: Pokemon) {

        val idCampeao = AuthDao.getCurrentUser()!!.uid
        pokemon.campeaoId = idCampeao
        val task = PokemonDao.inserir(pokemon, idCampeao)

        task.addOnSuccessListener {
            msg.value = "Pokemon criado com sucesso."
            status.value = true
        }.addOnFailureListener {
            msg.value = it.message
        }
    }
}