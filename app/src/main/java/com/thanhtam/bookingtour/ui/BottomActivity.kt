package com.thanhtam.bookingtour.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.data.responses.ResponseTour
import com.thanhtam.bookingtour.databinding.ActivityBottomBinding
import com.thanhtam.bookingtour.ui.adapter.AllTourAdapter
import com.thanhtam.bookingtour.ui.adapter.TourCheapAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*


@AndroidEntryPoint
class BottomActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
        setupActionBarWithNavController(navController)

        setupSmoothBottomMenu()
        supportActionBar?.hide()
        fetchTourCheap()
        fetchAllTours()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun fetchTourCheap() {
        val viewModel = ViewModelProvider(this)[BottomActivityViewModel::class.java]
        viewModel.getRecyclerListDataObserver().observe(this, Observer<ResponseTour> {

            if (it != null) {
                showToursCheap(it)
//                TourCheapAdapter(it).data.data.data.size
                TourCheapAdapter(it).notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@BottomActivity,
                    "Error in getting data from api",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        viewModel.makeApiCallTourCheap()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun fetchAllTours() {
        val viewModel = ViewModelProvider(this)[BottomActivityViewModel::class.java]
        viewModel._getRecyclerListDataObserver().observe(this, Observer<ResponseTour> {

            if (it != null) {
                showTours(it, this)
//                AllTourAdapter(it).data.data.data.size
                AllTourAdapter(it, this).notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@BottomActivity,
                    "Error in getting data from api",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        viewModel.makeApiCallAllTour()
    }

    private fun showToursCheap(tours: ResponseTour) {
        rv_topcheap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_topcheap.adapter = TourCheapAdapter(tours)
        rv_topcheap.setHasFixedSize(true)

    }

    private fun showTours(tours: ResponseTour, c: Context) {
        rv_alltour.layoutManager = LinearLayoutManager(this)
        rv_alltour.adapter = AllTourAdapter(tours, this)
        rv_alltour.setHasFixedSize(true)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.another_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.another_item_1 -> {
                showToast("Another Menu Item 1 Selected")
            }

            R.id.another_item_2 -> {
                showToast("Another Menu Item 2 Selected")
            }

            R.id.another_item_3 -> {
                showToast("Another Menu Item 3 Selected")
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.main)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}