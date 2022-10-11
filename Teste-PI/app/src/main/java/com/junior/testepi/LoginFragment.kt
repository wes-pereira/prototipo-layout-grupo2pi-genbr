package com.junior.testepi


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.junior.testepi.databinding.FragmentCadBinding
import com.junior.testepi.databinding.FragmentLoginBinding
import com.junior.testepi.util.Validator

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.btnLogin.setOnClickListener{
            if(!Validator.validarEmail(binding.verEmail.text.toString())){
                binding.verEmail.error = "Preencha com o email de acesso"
                binding.verEmail.requestFocus()
                return@setOnClickListener
            }
            if(!Validator.validarSenha(binding.verSenha.text.toString())){
                binding.verSenha.error = "Prencha o senha de acesso"
                binding.verSenha.requestFocus()
                return@setOnClickListener
            }
            findNavController().navigate(R.id.action_loginFragment_to_listFragment)
        }

        binding.btnCadastro.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_cadFragment)
        }

        return binding.root
    }
}
