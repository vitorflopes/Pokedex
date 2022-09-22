package com.example.pokedex.ui.pokemons.detalhes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDetalhesPokemonBinding

class DetalhesPokemonFragment : Fragment() {

    var _binding: FragmentDetalhesPokemonBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: DetalhesPokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalhesPokemonBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(DetalhesPokemonViewModel::class.java)



        return view
    }
}