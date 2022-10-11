package com.junior.testepi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.junior.testepi.model.Cadastro
import com.junior.testepi.databinding.FragmentCadBinding
import com.junior.testepi.util.Validator

class CadFragment : Fragment() {
    private lateinit var binding: FragmentCadBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadBinding.inflate(layoutInflater, container, false)

        val model: MainViewModel by viewModels()
        mainViewModel = ViewModelProvider(this)[model::class.java]
        binding.btnPronto.setOnClickListener {
            inserirNoBanco()
            if (!Validator.validarNome(binding.editNome.text.toString())) {
                binding.editNome.error = "Prencha o nome corretamente"
                binding.editNome.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.validarIdade(binding.editNasc.text.toString())) {
                binding.editNasc.error = "Verique a idade"
                binding.editNasc.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.validarEmail(binding.editEmail.text.toString())) {
                binding.editEmail.error = "Prencha o email completo"
                binding.editEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.validarSenha(binding.editSenha.text.toString())) {
                binding.editSenha.error = "Prencha o senha de acesso"
                binding.editSenha.requestFocus()
                return@setOnClickListener
            }
            findNavController().navigate(R.id.action_cadFragment_to_listFragment)
        }

        binding.btnVoltar.setOnClickListener {
            findNavController().navigate(R.id.action_cadFragment_to_loginFragment)
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.spinner.selectedItem
            }
        }
        return binding.root
    }
        fun verifiCampos(
            nome: String,
            dataNasc: String,
            email: String,
            genero: String,
            senha: String
        ): Boolean {
            return !(nome == "" || dataNasc == "" || email == "" || genero == "" || senha == "")
        }

        fun inserirNoBanco() {
            val nome = binding.editNome.text.toString()
            val dataNasc = binding.editNasc.text.toString()
            val email = binding.editEmail.text.toString()
            val genero = binding.spinner.selectedItem.toString()
            val senha = binding.editSenha.text.toString()

            if (verifiCampos(nome, dataNasc, email, genero, senha)) {
                Toast.makeText(context, "Cadastro feito!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_cadFragment_to_listFragment)

            } else {
                Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_LONG).show()
            }
        }
}
