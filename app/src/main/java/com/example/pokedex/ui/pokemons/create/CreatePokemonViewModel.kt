package com.example.pokedex.ui.pokemons.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.model.apiModel.*
import com.example.pokedex.service.RetroFit
import kotlinx.coroutines.launch

class CreatePokemonViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()
    val msg = MutableLiveData<String>()

    var pokemon = MutableLiveData<Pokemon>()
    var species = MutableLiveData<PokemonSpecies>()
    val game = MutableLiveData<VersionGroup>()

    var listItens = MutableLiveData<List<Item>>()
    var listAbilities = MutableLiveData<List<Ability>>()
    var listHeldItems = MutableLiveData<List<Item>>()
    var listMoves = MutableLiveData<List<Move>>()
    var listStats = MutableLiveData<List<Stat>>()
    var listTypes = MutableLiveData<List<Type>>()
    var listNature = MutableLiveData<List<Nature>>()
    var listGames = MutableLiveData<List<VersionGroup>>()

    fun retornaPokemon(pokemonName: String) {
        viewModelScope.launch {
            val helpListAbility = arrayListOf<Ability>()
            val helpListMove = arrayListOf<Move>()
            val helpListStat = arrayListOf<Stat>()
            val helpListType = arrayListOf<Type>()

            pokemon.value = RetroFit.pokemonsService.getPokemon(pokemonName)
            species.value = RetroFit.pokemonsService.getSpecies(pokemon.value!!.id.toString())
            listNature.value = RetroFit.pokemonsService.getNature().results
            listItens.value = RetroFit.pokemonsService.getItens().results
            listGames.value = RetroFit.pokemonsService.getGames().results

            for (ability in pokemon.value!!.abilities) {
                val ablt = RetroFit.pokemonsService.getAbility(ability.ability.name)
                helpListAbility.add(ablt)
            }
            listAbilities.value = helpListAbility

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
}