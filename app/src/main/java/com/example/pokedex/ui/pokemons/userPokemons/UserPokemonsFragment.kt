package com.example.pokedex.ui.pokemons.userPokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.pokedex.R
import com.example.pokedex.adapter.AdapterPokemon
import com.example.pokedex.databinding.FragmentUserPokemonsBinding
import com.example.pokedex.ui.pokemons.form.AllPokemonsFragmentDirections

class UserPokemonsFragment : Fragment() {

    private var _binding: FragmentUserPokemonsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserPokemonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserPokemonsBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(UserPokemonsViewModel::class.java)

        viewModel.retornaPokemonsUser()

        viewModel.pokemonsList.observe(viewLifecycleOwner) {
            val listUserPokemons = it
        }

        return view
    }
}