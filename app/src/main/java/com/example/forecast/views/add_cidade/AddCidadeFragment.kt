package com.example.forecast.views.add_cidade

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forecast.adapters.CidadeAdapter
import com.example.forecast.databinding.FragmentAddCidadeBinding
import com.example.forecast.services.WebService
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddCidadeFragment : Fragment() {

    private lateinit var binding: FragmentAddCidadeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentAddCidadeBinding.inflate(layoutInflater)

        var viewModel = ViewModelProvider(this).get(AddCidadeViewModel::class.java)

        var adapter = CidadeAdapter()
        binding.listAddCidades.layoutManager = LinearLayoutManager(context)
        binding.listAddCidades.adapter = adapter

        viewModel.findAll.observe(viewLifecycleOwner, { cidades ->
            adapter.cidades = cidades
        })

        loadCidades()

        return binding.root
    }

    fun loadCidades(): Boolean {
        val webService = WebService(this.requireActivity())
        runBlocking {
            launch {
                webService.getAll()
            }
        }
        return true
    }

}