package com.example.dog_4221

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dog_4221.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel : DogViewModel by activityViewModels()
    var idImages: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idImages = it.getString("status", "")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        var adapter = ImageAdapter()
        binding.ivImage.adapter = adapter
        binding.ivImage.layoutManager = GridLayoutManager(context, 2)
        viewModel.getDogById(idImages).observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
            it?.let {
                Log.d("segundo fragmento", "$it")
                adapter.update(it)
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                if (it.fav) {
                   it.fav = false
                   viewModel.updateFavImages(it)
                   Toast.makeText(context, "Eliminado de fav", Toast.LENGTH_LONG).show()
                } else {
                it.fav = true
                viewModel.updateFavImages(it)
                Toast.makeText(context, "Añadido a fav", Toast.LENGTH_LONG).show()
            }
            }
        })

    }
}