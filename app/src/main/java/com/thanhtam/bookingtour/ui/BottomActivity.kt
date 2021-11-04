package com.thanhtam.bookingtour.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanhtam.bookingtour.R
import com.thanhtam.bookingtour.data.network.Resource
import com.thanhtam.bookingtour.data.network.TourApi
import com.thanhtam.bookingtour.data.responses.ResponseTour
import com.thanhtam.bookingtour.databinding.ActivityBottomBinding
import com.thanhtam.bookingtour.databinding.FragmentSearchBinding
import com.thanhtam.bookingtour.ui.adapter.AllTourAdapter
import com.thanhtam.bookingtour.ui.adapter.TourCheapAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class BottomActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityBottomBinding
    private val viewModel by viewModels<BottomActivityViewModel>()
    private lateinit var allTourAdapter: AllTourAdapter
    private lateinit var tourCheapAdapter: TourCheapAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this)[BottomActivityViewModel::class.java]
        setContentView(binding.root)


        navController = findNavController(R.id.main_fragment)
        setupActionBarWithNavController(navController)
        setupSmoothBottomMenu()
        supportActionBar?.hide()
        fetchAllTours()
        fetchTourCheap()

        viewModel.responseTourCheap.observe(this, Observer {
            when(it){
                is Resource.Success -> {
                    lifecycleScope.launch{
                        viewModel.getTour()
                        fetchTourCheap()
                    }
                }
                is Resource.Failure -> {
                    Log.v("Error", "Sai o day ne")
                }
            }
        })
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

    private fun showToursCheap(tours: ResponseTour) {

//        _binding.apply {
//            rvTopcheap.apply {
//                adapter = TourCheapAdapter(tours)
//                layoutManager =
//                    LinearLayoutManager(this@BottomActivity, LinearLayoutManager.HORIZONTAL, false)
//                setHasFixedSize(true)
//            }
//        }
        rv_topcheap.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_topcheap.adapter = TourCheapAdapter(tours)
        rv_topcheap.setHasFixedSize(true)
        rv_topcheap.setItemViewCacheSize(10)
    }

    private fun showTours(tours: ResponseTour) {
//        _binding.apply {
//            rvAlltour.apply {
//                adapter = AllTourAdapter(tours)
//                layoutManager = LinearLayoutManager(this@BottomActivity)
//                setHasFixedSize(true)
//            }
//        }

        rv_alltour.layoutManager = LinearLayoutManager(this)
        rv_alltour.adapter = AllTourAdapter(tours)
        rv_alltour.setHasFixedSize(true)
        rv_alltour.setItemViewCacheSize(10)
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