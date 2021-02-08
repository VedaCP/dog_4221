package com.example.dog_4221

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dog_4221.databinding.FragmentFirstBinding
import com.example.dog_4221.model.DogViewModel
import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.remote.WrapperBreed

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : DogViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BreedDogAdapter()
        binding.rvBreed.adapter = adapter
        binding.rvBreed.layoutManager = GridLayoutManager(context, 2)

        //Observador la vieja confiable
        viewModel.breedEntityLiveDataFromDB.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("LISTADO", it.toString())
                adapter.update(it)
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                val bundle = Bundle()
                bundle.putString("status", it.breed)
                viewModel.getFetchBreedWhitCoroutines(it.breed)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        })

    }
}