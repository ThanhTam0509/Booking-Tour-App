package com.thanhtam.bookingtour.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.databinding.FragmentLoginBinding
import com.thanhtam.bookingtour.data.network.AuthApi
import com.thanhtam.bookingtour.data.network.Resource
import com.thanhtam.bookingtour.data.repository.AuthRepository
import com.thanhtam.bookingtour.ui.auth.base.BaseFragment
import com.thanhtam.bookingtour.ui.auth.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = view?.let { FragmentLoginBinding.bind(it) }!!
        binding.progress.visible(false)
        binding.btnLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progress.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(
                            it.value.token!!
                        )
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.etPassword.addTextChangedListener {
            val email = binding.etEmail.text.toString().trim()
            binding.btnLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.btnLogin.setOnClickListener {
           login()
        }
        binding.swipeRight.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        binding.progress.visible(true)
        viewModel.login(email, password)
    }
}