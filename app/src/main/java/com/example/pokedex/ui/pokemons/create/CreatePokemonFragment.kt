package com.example.pokedex.ui.pokemons.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

            val listAbilit = ArrayList<String>()
            for (abilit in it.abilities) {
                listAbilit.add(abilit.ability.name)
            }
            val spinnerAbi: Spinner = binding.spinnerAbi
            val abiArrayAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, listAbilit)
            spinnerAbi.adapter = abiArrayAdapter

        }

        viewModel.listItens.observe(viewLifecycleOwner) {
            val listNameItem = arrayListOf<String>()

            for (item in it) {
                listNameItem.add(item.name)
            }

            val adapterItem : ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, listNameItem)

            val autoTextItem : AutoCompleteTextView = binding.acItem
            autoTextItem.setAdapter(adapterItem)
        }

        viewModel.listGames.observe(viewLifecycleOwner) {
            val listGames = ArrayList<String>()
            for (game in it) {
                listGames.add(game.name)
            }
            val spinnerGame : Spinner = binding.spinnerGame
            val abiArrayAdapter: ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, listGames)
            spinnerGame.adapter = abiArrayAdapter

            spinnerGame
                .onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long
                    ) {
                        val versionGroup = viewModel.listGames.value?.get(p2)?.name!!.toString()
                        val pokemon = viewModel.pokemon.value
                        if (versionGroup != null) {
                            val listMoves = arrayListOf<String>()
                            for (move in pokemon!!.moves) {
                                for (versionG in move.version_group_details) {
                                    if (versionG.version_group.name == versionGroup) {
                                        listMoves.add(move.move.name)
                                    }
                                }
                            }
                            val adapterMove: ArrayAdapter<String> =
                                ArrayAdapter<String>(
                                    requireContext(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    listMoves
                                )

                            val autoTextM1: AutoCompleteTextView = binding.acMove1
                            autoTextM1.setAdapter(adapterMove)
                            val autoTextM2: AutoCompleteTextView = binding.acMove2
                            autoTextM2.setAdapter(adapterMove)
                            val autoTextM3: AutoCompleteTextView = binding.acMove3
                            autoTextM3.setAdapter(adapterMove)
                            val autoTextM4: AutoCompleteTextView = binding.acMove4
                            autoTextM4.setAdapter(adapterMove)
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        Toast.makeText(
                            requireContext(),
                            "Selecione alguma opção.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        return view
    }
}