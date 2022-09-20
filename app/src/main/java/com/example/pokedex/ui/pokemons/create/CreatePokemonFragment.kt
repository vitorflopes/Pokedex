package com.example.pokedex.ui.pokemons.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentCreatePokemonBinding
import com.squareup.picasso.Picasso

class CreatePokemonFragment : Fragment() {

    var _binding: FragmentCreatePokemonBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: CreatePokemonViewModel
    private val argumentos by navArgs<CreatePokemonFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePokemonBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(CreatePokemonViewModel()::class.java)

        viewModel.retornaPokemon(argumentos.pokemonName)

        viewModel.listNature.observe(viewLifecycleOwner) {
            val natArray = ArrayList<String>()

            for(n in it){
                natArray.add(n.name)
            }

            val spinnernat: Spinner = binding.spinnerNat
            val natArrayAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, natArray)
            spinnernat.adapter = natArrayAdapter
        }

        viewModel.pokemon.observe(viewLifecycleOwner) {
            Picasso.get().load(it.sprites.front_default).into(binding.ivPkmn)
            binding.inputName.setText(it.name)
            binding.acSpecies.setText(it.species.name)

            binding.tvType1.text = it.types[0].type.name
            if (it.types.size > 1) {
                binding.tvType2.text = it.types[1].type.name
            }


        }

        return view
    }
}