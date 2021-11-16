package com.thanhtam.bookingtour.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.ui.BottomActivity
import com.thanhtam.bookingtour.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogout.setOnClickListener {
            val intent = Intent (activity, AuthActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}