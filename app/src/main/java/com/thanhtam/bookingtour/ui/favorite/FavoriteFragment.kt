package com.thanhtam.bookingtour.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.databinding.FragmentFavoriteBinding
import com.thanhtam.bookingtour.ui.BottomActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_Explorer.setOnClickListener {
//            findNavController().navigate(R.id.action_favoriteFragment_to_searchFragment)
            val intent = Intent (activity, BottomActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}