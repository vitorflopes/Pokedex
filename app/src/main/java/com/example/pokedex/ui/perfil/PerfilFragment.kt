package com.example.pokedex.ui.perfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokedex.R
import com.example.pokedex.dao.AuthDao
import com.example.pokedex.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PerfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(PerfilViewModel::class.java)

        viewModel.numPokemons()
        viewModel.detalhesCampeao()

        viewModel.campeao.observe(viewLifecycleOwner) {
            val nomeCampeao = it.strNome()

            binding.tvPerfilNomeCampeao.text = nomeCampeao
        }

        viewModel.numPokemonsUser.observe(viewLifecycleOwner) {
            binding.tvNumeroPokemonsUsuario.text = it.toString()
        }

        binding.btnPerfilCampeoes.setOnClickListener {
            findNavController().navigate(R.id.campeoesListaFragment)
        }

        binding.btnPokemons.setOnClickListener {
            findNavController().navigate(R.id.allPokemonsFragment)
        }

        binding.btnPokemons.setOnClickListener {
            findNavController().navigate(R.id.allPokemonsFragment)
        }

        binding.btnSair.setOnClickListener {
            AuthDao.deslogar()
            findNavController().navigate(R.id.signInFragment)
        }

        binding.btnSeusPokemons.setOnClickListener {
            findNavController().navigate(R.id.userPokemonsFragment)
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        val user = viewModel.retornaUsuarioLogado()
        if (user == null) {
            val direction = PerfilFragmentDirections.actionPerfilFragmentToSignInFragment()
            findNavController().navigate(direction)
        }
    }
}