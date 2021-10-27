package com.thanhtam.bookingtour.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.data.network.AuthApi
import com.thanhtam.bookingtour.data.network.Resource
import com.thanhtam.bookingtour.data.repository.AuthRepository
import com.thanhtam.bookingtour.databinding.FragmentLoginBinding
import com.thanhtam.bookingtour.databinding.FragmentRegisterBinding
import com.thanhtam.bookingtour.ui.auth.base.BaseFragment
import com.thanhtam.bookingtour.ui.auth.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progress.visible(false)
        binding.btnRegister.enable(false)

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
            binding.progress.visible(false)
            when (it) {
                is Resource.Success -> {
//                    viewModel.saveAuthToken(it.value.token)
//                    requireActivity().startNewActivity(HomeActivity::class.java)
                    Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_LONG).show()
                    Log.v("Error", "Check this Success")
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Register Failure", Toast.LENGTH_LONG).show()
                    Log.v("Error", "Check this Failure")
                }
            }
        })

        binding.etPassword.addTextChangedListener {
            val email = binding.etEmail.text.toString().trim()
            val name = binding.etName.text.toString().trim()
            binding.btnRegister.enable(email.isNotEmpty() && it.toString().isNotEmpty())
            binding.btnRegister.enable(name.isNotEmpty() && it.toString().isNotEmpty())
        }


        binding.btnRegister.setOnClickListener {
           register()
        }
        binding.swipeLeft.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

    private fun register() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val passwordConfirm = binding.etRepassword.toString().trim()
        binding.progress.visible(true)
        viewModel.register(name, email, password, passwordConfirm)
    }
}