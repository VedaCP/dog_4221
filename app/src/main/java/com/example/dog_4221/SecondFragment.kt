package com.example.dog_4221

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dog_4221.databinding.FragmentFirstBinding
import com.example.dog_4221.databinding.FragmentSecondBinding
import com.example.dog_4221.model.DogViewModel
import java.util.*

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
        binding.ivImage.layoutManager = LinearLayoutManager(context)
        viewModel.getDogById(idImages).observe(viewLifecycleOwner,
            androidx.lifecycle.Observer {
            it?.let {
                Log.d("segundo fragmento", "$it")
                adapter.update(it)
            }
        })

    }
}