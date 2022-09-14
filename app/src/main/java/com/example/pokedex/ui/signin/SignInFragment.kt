package com.example.pokedex.ui.signin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.perfilFragment)
            }
        }

        viewModel.msg.observe(viewLifecycleOwner) {
            if (it.isNotBlank())
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        }

        lerPref(binding.edtTxtSigninEmail)

        binding.btnSignInAcessar.setOnClickListener {
            val email = binding.edtTxtSigninEmail.text.toString()
            val password = binding.edtTxtSigninSenha.text.toString()
            val lembrar = binding.cbSigninLembrar.isChecked
            if (lembrar) {
                salvarPref(email)
            }
            viewModel.autenticar(email, password)
        }

        binding.txtVwSignupLink.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        val imagemApi = binding.ivTesteFoto
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/1.png")
            .into(imagemApi)

        return view
    }

    fun lerPref (edtText: EditText) {
        val pref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val email = pref.getString("email", null)
        edtText.setText(email)
    }

    fun salvarPref (email: String) {
        val pref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (pref.edit()) {
            putString("email", email)
            apply()
        }
    }
}