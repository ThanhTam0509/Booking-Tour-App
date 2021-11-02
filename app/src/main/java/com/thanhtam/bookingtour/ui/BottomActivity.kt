package com.thanhtam.bookingtour.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.data.network.TourApi
import com.thanhtam.bookingtour.data.responses.ResponseTour
import com.thanhtam.bookingtour.databinding.ActivityBottomBinding
import com.thanhtam.bookingtour.ui.adapter.AllTourAdapter
import com.thanhtam.bookingtour.ui.adapter.TourCheapAdapter
import com.thanhtam.bookingtour.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class BottomActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityBottomBinding
    private lateinit var viewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
        setupActionBarWithNavController(navController)
        setupSmoothBottomMenu()
        supportActionBar?.hide()
        fetchTourCheap()
        fetchAllTours()
//        viewModelForTourCheap()
//        viewModelForAllTour()
    }

    private fun fetchTourCheap() {
        TourApi().getTour().enqueue(object :
            retrofit2.Callback<ResponseTour> {
            override fun onResponse(
                call: Call<ResponseTour>,
                response: Response<ResponseTour>
            ) {

                val tours = response.body()

                tours?.let {
                    showToursCheap(it)
                }
            }

            override fun onFailure(call: Call<ResponseTour>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
//    private fun viewModelForTourCheap(){
//        viewModel.responseTourCheap.observe(this, Observer {
//            fetchTourCheap()
//        })
//    }

    private fun fetchAllTours() {
        TourApi().getAllTour().enqueue(object :
            retrofit2.Callback<ResponseTour> {
            override fun onResponse(
                call: Call<ResponseTour>,
                response: Response<ResponseTour>
            ) {

                val tours = response.body()

                tours?.let {
                    showTours(it)
                }
            }

            override fun onFailure(call: Call<ResponseTour>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

//    private fun viewModelForAllTour() {
//        viewModel.responseAllTours.observe(this, Observer {
//            fetchAllTours()
//        })
//    }

    private fun showToursCheap(tours: ResponseTour) {
        rv_topcheap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_topcheap.adapter = TourCheapAdapter(tours)
        viewModel.getTour()
    }

    private fun showTours(tours: ResponseTour) {
        rv_alltour.layoutManager = LinearLayoutManager(this)
        rv_alltour.adapter = AllTourAdapter(tours)
        viewModel.getAllTours()
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