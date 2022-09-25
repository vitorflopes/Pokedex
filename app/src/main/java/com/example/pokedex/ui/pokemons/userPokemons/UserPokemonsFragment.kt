package com.example.pokedex.ui.pokemons.userPokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.adapter.AdapterCreatedPokemon
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

        binding.rvPokemonsUser.layoutManager = LinearLayoutManager(context)
        //binding.rvPokemons.layoutManager = GridLayoutManager(context, 2)
        binding.rvPokemonsUser.setHasFixedSize(true)
        viewModel.pokemonsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.progressBar4.isVisible = false

                val lac = LayoutAnimationController(AnimationUtils.loadAnimation(context, R.anim.item_anim))
                lac.delay = 0.10f
                lac.order = LayoutAnimationController.ORDER_NORMAL

                val adapterPokemon = AdapterCreatedPokemon(requireContext(), it)
                binding.rvPokemonsUser.layoutAnimation = lac
                binding.rvPokemonsUser.adapter = adapterPokemon
                adapterPokemon.setOnItemClickListener(object : AdapterCreatedPokemon.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val pokemonId = it[position].id
                        val direction = UserPokemonsFragmentDirections.actionUserPokemonsFragmentToDetalhesPokemonFragment(pokemonId!!)

                        findNavController().navigate(direction)
                    }
                })
            }

            else {
                binding.progressBar4.isVisible = false

                binding.textView13.isVisible = true
                binding.btnCriarPokemon.isVisible = true
            }
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            binding.progressBar4.isVisible = false
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        binding.btnVoltarUserPokemons.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCriarPokemon.setOnClickListener {
            findNavController().navigate(R.id.allPokemonsFragment)
        }

        return view
    }
}