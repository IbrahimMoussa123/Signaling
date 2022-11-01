package com.example.signaling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.signaling.databinding.FragmentAppBinding
import com.example.signaling.databinding.FragmentLoginBinding

class AppFragment : Fragment() {

    private var _binding: FragmentAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAppBinding.inflate(inflater, container, false)
        binding.mapsButton.setOnClickListener {
            findNavController().navigate(AppFragmentDirections.actionAppFragmentToMapsFragment())

        }

        return binding.root
    }

}