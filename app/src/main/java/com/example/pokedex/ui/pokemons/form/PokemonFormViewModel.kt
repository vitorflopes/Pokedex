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
    var listHeldItems = MutableLiveData<List<Item>>()
    var listMoves = MutableLiveData<List<Move>>()
    var listStats = MutableLiveData<List<Stat>>()
    var listTypes = MutableLiveData<List<Type>>()

    fun retornaPokemon(pokemonName: String) {

        viewModelScope.launch {
            val helpListAbility = arrayListOf<Ability>()
            val helpListForm = arrayListOf<PokemonForm>()
            val helpListItem = arrayListOf<Item>()
            val helpListMove = arrayListOf<Move>()
            val helpListStat = arrayListOf<Stat>()
            val helpListType = arrayListOf<Type>()

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

            for (item in pokemon.value!!.held_items) {
                val itm = RetroFit.pokemonsService.getItem(item.item.name)
                helpListItem.add(itm)
            }
            listHeldItems.value = helpListItem

            for (move in pokemon.value!!.moves) {
                val mv = RetroFit.pokemonsService.getMove(move.move.name)
                helpListMove.add(mv)
            }
            listMoves.value = helpListMove

            for (stat in pokemon.value!!.stats) {
                val stt = RetroFit.pokemonsService.getStat(stat.stat.name)
                helpListStat.add(stt)
            }
            listStats.value = helpListStat

            for (type in pokemon.value!!.types) {
                val tp = RetroFit.pokemonsService.getType(type.type.name)
                helpListType.add(tp)
            }
            listTypes.value = helpListType
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