package com.example.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.apiModel.Pokemon
import com.squareup.picasso.Picasso

class AdapterPokemon(private val context: Context, private val pokemons: MutableList<Pokemon>)
    : RecyclerView.Adapter<AdapterPokemon.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.pokemon_list, parent, false)
        val holder = PokemonViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        Picasso.get().load(pokemons[position].sprites!!.front_default).into(holder.imgFront)
        Picasso.get().load(pokemons[position].sprites!!.back_default).into(holder.imgBack)
        holder.name.text = pokemons[position].name
    }

    override fun getItemCount(): Int = pokemons.size

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvNamePokemon)
        val imgFront = itemView.findViewById<ImageView>(R.id.ivFrontPoke)
        val imgBack = itemView.findViewById<ImageView>(R.id.ivBackPoke)
    }
}