package com.example.pokedex.ui.pokemons.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
        viewModel = ViewModelProvider(this, AllPokemonsVMF(requireContext())).get(AllPokemonsViewModel::class.java)

        binding.rvPokemons.layoutManager = LinearLayoutManager(context)
        //binding.rvPokemons.layoutManager = GridLayoutManager(context, 2)
        binding.rvPokemons.setHasFixedSize(true)

        viewModel.pokemonsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {

                val lac = LayoutAnimationController(AnimationUtils.loadAnimation(context, R.anim.item_anim))
                lac.delay = 0.10f
                lac.order = LayoutAnimationController.ORDER_NORMAL

                val adapterPokemon = AdapterPokemon(requireContext(), it)
                binding.rvPokemons.layoutAnimation = lac
                binding.rvPokemons.adapter = adapterPokemon
                adapterPokemon.setOnItemClickListener(object : AdapterPokemon.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val pokemonName = it[position].name

                        val direction = AllPokemonsFragmentDirections
                            .actionAllPokemonsFragmentToPokemonFormFragment(pokemonName)
                        findNavController().navigate(direction)
                    }
                })

                binding.btnProximaPagAP.isVisible = true
                binding.btnAnteriosPagAP.isVisible = true
                binding.progressBar.isVisible = false
            }
        }
        viewModel.offsetObs.observe(viewLifecycleOwner) {
            binding.btnAnteriosPagAP.isVisible = it != 0
        }

        binding.btnProximaPagAP.setOnClickListener {
            binding.progressBar.isVisible = true
            binding.btnProximaPagAP.isVisible = false
            binding.btnAnteriosPagAP.isVisible = false
            viewModel.retornaOffsetAdd(requireContext())
        }

        binding.btnAnteriosPagAP.setOnClickListener {
            binding.progressBar.isVisible = true
            binding.btnProximaPagAP.isVisible = false
            binding.btnAnteriosPagAP.isVisible = false
            viewModel.retornaOffsetMin(requireContext())
        }

        binding.btnVoltarAllPokemons.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}