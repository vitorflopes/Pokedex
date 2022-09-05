package com.example.pokedex.model

class PokemonsResponse (
    val count: Int? = null,
    val next: String? = null,
    val previous: Int? = null,
    val results: List<Pokemon>? = null
) {}