package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.pokedex.databinding.FragmentPokemonFormBinding
import com.google.android.material.snackbar.Snackbar

class PokemonFormFragment : Fragment() {

    var _binding: FragmentPokemonFormBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: PokemonFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonFormBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(PokemonFormViewModel::class.java)

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

        binding.btnCriar.setOnClickListener {
            val nomePokemon = binding.ptNomePokemon.text.toString()
            val categoria = binding.ptCategoria.text.toString()
            val hp = binding.ptHP.text.toString()
            val ataque = binding.ptAtaque.text.toString()
            val defesa = binding.ptDefesa.text.toString()

            //val pokemon = Pokemon(nomePokemon, categoria, hp.toInt(), ataque.toInt(), defesa.toInt())
            //viewModel.salvar(pokemon)
        }

        return view
    }

    private fun showSnackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }
}