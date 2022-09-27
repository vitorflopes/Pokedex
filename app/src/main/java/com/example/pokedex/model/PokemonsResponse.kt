package com.example.pokedex.model

import com.example.pokedex.model.apiModel.Pokemon

class PokemonsResponse (
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Pokemon>? = null
) {}