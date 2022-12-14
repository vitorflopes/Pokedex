package com.example.pokedex.ui.pokemons.detalhes

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.toColor
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDetalhesPokemonBinding
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.squareup.picasso.Picasso

class DetalhesPokemonFragment : Fragment() {

    var _binding: FragmentDetalhesPokemonBinding? = null
    val binding get() = _binding!!
    private lateinit var viewModel: DetalhesPokemonViewModel
    private val argumentos by navArgs<DetalhesPokemonFragmentArgs>()

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalhesPokemonBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(DetalhesPokemonViewModel::class.java)

        viewModel.retornaPokemon(argumentos.pokemonId)

        viewModel.msg.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.pokemon.observe(viewLifecycleOwner) {
            if (it != null) {
                val pokemonColor = resources.getColor(retornaCor(it.color!!))
                binding.clCardPokemonDP.setBackgroundColor(pokemonColor)

                Picasso.get().load(it.spriteFront).into(binding.ivSpritePokemonDP)
                if (!it.gender!!) {
                    binding.imGenderDP.setImageResource(R.drawable.ic_baseline_female_24)
                }

                binding.tvLevelDP.text = it.lvl
                binding.tvSpeciesDP.text = it.species
                binding.tvHbilidadeDP.text = it.ability
                binding.tvNatureDP.text = it.nature

                binding.tvHpDP.text = it.iv!![0]
                ObjectAnimator.ofInt(binding.pbHpDP, "progress", it.iv!![0].toInt())
                    .setDuration(2000).start()
                binding.tvAttackDP.text = it.iv!![1]
                ObjectAnimator.ofInt(binding.pbAttackDP, "progress", it.iv!![1].toInt())
                    .setDuration(2000).start()
                binding.tvDefenseDP.text = it.iv!![2]
                ObjectAnimator.ofInt(binding.pbDefenseDP, "progress", it.iv!![2].toInt())
                    .setDuration(2000).start()
                binding.tvSpAttackDP.text = it.iv!![3]
                ObjectAnimator.ofInt(binding.pbSpAttackDP, "progress", it.iv!![3].toInt())
                    .setDuration(2000).start()
                binding.tvSpDefenseDP.text = it.iv!![4]
                ObjectAnimator.ofInt(binding.pbSpDefenseDP, "progress", it.iv!![4].toInt())
                    .setDuration(2000).start()
                binding.tvSpeedDP.text = it.iv!![5]
                ObjectAnimator.ofInt(binding.pbSpeedDP, "progress", it.iv!![5].toInt())
                    .setDuration(2000).start()

                binding.tvEvHpDP.text = it.ev!![0]
                binding.tvEvAtkDP.text = it.ev!![1]
                binding.tvEvDefDP.text = it.ev!![2]
                binding.tvEvSpAtkDP.text = it.ev!![3]
                binding.tvEvSpDefDP.text = it.ev!![4]
                binding.tvEvSpeedDP.text = it.ev!![5]

                binding.tvMove1DP.text = it.moves!![0]
                binding.tvMove2DP.text = it.moves!![1]
                binding.tvMove3DP.text = it.moves!![2]
                binding.tvMove4DP.text = it.moves!![3]

                Picasso.get().load(it.spriteItem).into(binding.ivSpriteItemDP)
                binding.tvItemNameDP.text = it.item
            }
        }

        binding.btnExcluirPokemonDP.setOnClickListener {
            viewModel.excluirPokemon()
            Toast.makeText(requireContext(), "Pokemon excluido!", Toast.LENGTH_LONG).show()
            val direction = DetalhesPokemonFragmentDirections.actionDetalhesPokemonFragmentToPerfilFragment()
            findNavController().navigate(direction)
        }

        binding.btnVoltarDP.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }

    private fun retornaCor(nomeCor: String): Int {
        if (nomeCor == "black")
            return R.color.black_pokemon
        else if (nomeCor == "blue") {
            return R.color.blue_pokemon
        }
        else if (nomeCor == "brown") {
            return R.color.brown_pokemon
        }
        else if (nomeCor == "gray") {
            return R.color.gray_pokemon
        }
        else if (nomeCor == "green") {
            return R.color.green_pokemon
        }
        else if (nomeCor == "pink") {
            return R.color.pink_pokemon
        }
        else if (nomeCor == "purple") {
            return R.color.purple_pokemon
        }
        else if (nomeCor == "red") {
            return R.color.red_pokemon
        }
        else if (nomeCor == "white") {
            return R.color.white_pokemon
        }
        else if (nomeCor == "yellow") {
            return R.color.yellow_pokemon
        }

        return R.color.white_pokemon
    }
}