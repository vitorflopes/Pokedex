package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.adapter.AdapterPokemon
import com.example.pokedex.databinding.FragmentAllPokemonsBinding

class AllPokemonsFragment : Fragment() {

    var _binding: FragmentAllPokemonsBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: AllPokemonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllPokemonsBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(AllPokemonsViewModel::class.java)

        binding.rvPokemons.layoutManager = LinearLayoutManager(context)
        binding.rvPokemons.setHasFixedSize(true)

        viewModel.pokemonsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.progressBar.isVisible = false
                val adapterPokemon = AdapterPokemon(requireContext(), it!!)
                binding.rvPokemons.adapter = adapterPokemon
                adapterPokemon.setOnItemClickListener(object : AdapterPokemon.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val pokemonName = it[position].name

                        val direction = AllPokemonsFragmentDirections
                            .actionAllPokemonsFragmentToPokemonFormFragment(pokemonName)
                        findNavController().navigate(direction)
                    }
                })
            }
        }

        return view
    }
}
