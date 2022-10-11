package com.junior.testepi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.junior.testepi.databinding.FragmentFormBinding
import com.junior.testepi.model.Postagem
import com.junior.testepi.model.Tema


class FormFragment : Fragment() {
//    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentFormBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var temaSelecionado = 0L
    private var postagemSelecionada: Postagem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        carregarDados()
        mainViewModel.listTema()
        mainViewModel.myTemaResponse.observe(viewLifecycleOwner){
                response -> Log.d("requisicao", response.body().toString())
            spinnerTema(response.body())
        }
        binding.buttonPostar.setOnClickListener{
            inserirNoBanco()
        }
        binding.textLegenda

        return binding.root
    }

    private fun spinnerTema(listTema: List<Tema>?){
        if (listTema != null){
            binding.spinnerTema.adapter =
                ArrayAdapter(
                    requireContext(),
                    androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                    listTema
                )

            binding.spinnerTema.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val selected = binding.spinnerTema.selectedItem as Tema

                        temaSelecionado = selected.id

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
        }
    }
    private fun validarCampos( image: String, descricao: String): Boolean{
        return !( (descricao == "" || descricao.length < 5 || descricao.length > 200) ||
                        (image ==  "" || image.length < 10)
                )
    }

    private fun inserirNoBanco(){
        val image = binding.imgLink.text.toString()
        val desc = binding.textLegenda.text.toString()
        val tema = Tema(temaSelecionado, null, null)

        if(validarCampos( desc, image)){
            val postagem = Postagem(0, image, desc, tema)
            var salvar = ""
            if (postagemSelecionada != null){
                salvar = "tarefa atualizada"
                val postagem = Postagem( postagemSelecionada?.id!! ,image, desc,tema )
                mainViewModel.upDatePostagem(postagem)
                Toast.makeText(context, salvar, Toast.LENGTH_LONG).show()
            }else{
                salvar = "tarefa criada"
                val postagem = Postagem( postagemSelecionada?.id!! ,image, desc,tema )
                mainViewModel.addPost(postagem)
                Toast.makeText(context, salvar, Toast.LENGTH_LONG).show()
            }
            findNavController().navigate(R.id.action_formFragment_to_listFragment)
        }else{
            Toast.makeText(context, "Verique os campos 😢😶‍🌫️🤯", Toast.LENGTH_LONG).show()
        }
    }


    private fun carregarDados(){
        postagemSelecionada = mainViewModel.postagemSelecionada
        if (postagemSelecionada != null){
            binding.imgLink.setText(postagemSelecionada?.imagem)
            binding.textLegenda.setText(postagemSelecionada?.descricao)
            binding.spinnerTema.onItemSelectedListener.apply { postagemSelecionada}
        }
    }


}