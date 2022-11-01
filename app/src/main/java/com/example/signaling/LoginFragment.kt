package com.example.signaling

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.signaling.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val emailAdd = binding.emailtxt.text.toString()
            val password = binding.passtxt.text.toString()
            if(emailAdd.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(emailAdd, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val user = auth.currentUser
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAppFragment())

                    }else{
                        Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }

                }

            }else{
                Toast.makeText(requireContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT).show()

            }


        }
        binding.button.setOnClickListener {
            binding.loginButton.isEnabled = false
            binding.button.isEnabled = false
            binding.signupButton.visibility =View.VISIBLE
            binding.pass2txt.visibility = View.VISIBLE
            binding.button2.visibility = View.VISIBLE


        }
        binding.signupButton.setOnClickListener {
            val email = binding.emailtxt.text.toString()
            val pass = binding.passtxt.text.toString()
            val passConf = binding.pass2txt.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty() && passConf.isNotEmpty()){
                if(pass == passConf){
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            val user = auth.currentUser
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAppFragment())

                        }else{
                            Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(requireContext(), "passwords doesn't match", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(requireContext(), "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
        binding.button2.setOnClickListener {
            binding.loginButton.isEnabled = true
            binding.button.isEnabled = true
            binding.signupButton.visibility =View.GONE
            binding.pass2txt.visibility = View.GONE
            binding.button2.visibility = View.GONE

        }
        return binding.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Initialize Firebase Auth




        }
    }


}