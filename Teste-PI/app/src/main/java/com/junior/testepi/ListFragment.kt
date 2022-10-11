package com.junior.testepi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.junior.testepi.adapter.PostagemAdapter
import com.junior.testepi.adapter.PostagemClickListener
import com.junior.testepi.databinding.FragmentListBinding
import com.junior.testepi.model.Postagem

class ListFragment : Fragment(), PostagemClickListener {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(),R.anim.to_bottom_anim)}
    private var clicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        mainViewModel.listPostagem()
        //Configuração do recycleView

        val adapter = PostagemAdapter(this, mainViewModel)
        binding.recyclerPostagem.layoutManager = LinearLayoutManager(context)
        binding.recyclerPostagem.adapter = adapter
        binding.recyclerPostagem.setHasFixedSize(true)

        binding.floatingAdd.setOnClickListener{
            onAddButtonClick()

        }
        binding.floatingPhoto.setOnClickListener{
         mainViewModel.postagemSelecionada = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }
        binding.floatingUser.setOnClickListener{
//            Toast.makeText(requireContext(), "User clicado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_listFragment_to_profileFragment)
        }

        mainViewModel.myPostagemResponse.observe(viewLifecycleOwner){
                response -> if(response.body() != null){
            adapter.setList(response.body()!!)
        }
        }

        return binding.root

    }
    private fun onAddButtonClick(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }
    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.floatingUser.visibility = View.VISIBLE
            binding.floatingPhoto.visibility = View.VISIBLE
        }else{
            binding.floatingUser.visibility = View.INVISIBLE
            binding.floatingPhoto.visibility = View.INVISIBLE
        }
    }
    private fun setAnimation(clicked: Boolean){
        if (!clicked){
            binding.floatingUser.startAnimation(fromBottom)
            binding.floatingPhoto.startAnimation(fromBottom)
            binding.floatingAdd.startAnimation(rotateOpen)
        }else{
            binding.floatingUser.startAnimation(toBottom)
            binding.floatingPhoto.startAnimation(toBottom)
            binding.floatingAdd.startAnimation(rotateClose)
        }

    }
    override fun onPostagemClickListener(postagem: Postagem) {
        mainViewModel.postagemSelecionada = postagem
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }

}