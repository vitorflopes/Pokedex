package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.databinding.FragmentPokemonFormBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class PokemonFormFragment : Fragment() {

    var _binding: FragmentPokemonFormBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: PokemonFormViewModel
    private val argumentos by navArgs<PokemonFormFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonFormBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(PokemonFormViewModel()::class.java)

        viewModel.retornaPokemon(argumentos.pokemonName)

        viewModel.pokemon.observe(viewLifecycleOwner) {
            Picasso.get().load(it.sprites!!.front_default).into(binding.ivDexImg)
            binding.textView.text = it.name
            binding.tvHtnum.text = it.height.toString()
            binding.tvWtnum.text = it.weight.toString()
            binding.tvAbi1.text = it.abilities[0].ability.name

            for (abilit in it.abilities) {
                if (abilit.is_hidden) {
                    binding.tvHiddenAbi.text = abilit.ability.name
                }
            }
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                showSnackbar(view, it)
            }
        }

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }

        /*
        binding.btnCriar.setOnClickListener {
            val nomePokemon = binding.ptNomePokemon.text.toString()
            val categoria = binding.ptCategoria.text.toString()
            val hp = binding.ptHP.text.toString()
            val ataque = binding.ptAtaque.text.toString()
            val defesa = binding.ptDefesa.text.toString()

            //val pokemon = Pokemon(nomePokemon, categoria, hp.toInt(), ataque.toInt(), defesa.toInt())
            //viewModel.salvar(pokemon)
        }
         */

        return view
    }

    private fun showSnackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }
}