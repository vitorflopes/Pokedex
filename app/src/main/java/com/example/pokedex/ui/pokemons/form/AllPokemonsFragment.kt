package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.MainActivity
import com.example.pokedex.R
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
                val adapterPokemon = AdapterPokemon(requireContext(), it!!)
                binding.rvPokemons.adapter = adapterPokemon
                adapterPokemon.setOnItemClickListener(object : AdapterPokemon.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        Toast.makeText(context, "${position}", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }


        return view
    }
}
