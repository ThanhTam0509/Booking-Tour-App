package com.thanhtam.bookingtour.ui.booking

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.databinding.FragmentBookingBinding
import com.thanhtam.bookingtour.ui.BottomActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_booking.*

@AndroidEntryPoint
class BookingFragment : Fragment(R.layout.fragment_booking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_Booking.setOnClickListener {
//            findNavController().navigate(R.id.action_bookingFragment_to_searchFragment)
            val intent = Intent(activity, BottomActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}