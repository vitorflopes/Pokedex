package com.example.pokedex.ui.editar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
//import com.example.pokedex.database.AppDatabase
import com.example.pokedex.databinding.FragmentEditarBinding
/*
class EditarFragment : Fragment() {

    private var _binding: FragmentEditarBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditarViewModel
    private val argumentos by navArgs<EditarFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarBinding.inflate(inflater,container,false)
        val view = binding.root
        val appDatabase = AppDatabase.getInstance(requireContext())
        val campeaoDao = appDatabase.campeaoDao()
        val factory = EditarViewModelFactory(campeaoDao)
        viewModel = ViewModelProvider(this, factory).get(EditarViewModel::class.java)

        val campeaoId = argumentos.idCampeao
        viewModel.detalhesCampeao(campeaoId)

        viewModel.campeao.observe(viewLifecycleOwner) {
            val nomeCampeao = it.strNome()
            val email = it.strEmail()
            val senha = it.strSenha()

            binding.edtTxtSignupNomeCampeao.hint = nomeCampeao
            binding.edtTxtSignupEmail.hint = email
            binding.edtTxtSignupSenha.hint = senha
            binding.edtTxtSignupReSenha.hint = senha
        }

        binding.btnEditarSalvar.setOnClickListener {
            var campeao = viewModel.campeao.value

            var nomeCampeao = binding.edtTxtSignupNomeCampeao.text.toString()
            var email = binding.edtTxtSignupEmail.text.toString()
            var senha = binding.edtTxtSignupReSenha.text.toString()

            if (campeao != null) {
                viewModel.editarCampeao(nomeCampeao,email,senha,campeaoId)
            }

            findNavController().navigate(R.id.signInFragment)
        }

        return view
    }
}*/