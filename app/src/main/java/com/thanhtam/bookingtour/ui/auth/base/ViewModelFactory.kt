package com.thanhtam.bookingtour.ui.auth.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thanhtam.bookingtour.data.repository.AuthRepository
import com.thanhtam.bookingtour.data.repository.BaseRepository
import com.thanhtam.bookingtour.data.repository.TourRepository
import com.thanhtam.bookingtour.ui.BottomActivityViewModel
import com.thanhtam.bookingtour.ui.auth.AuthViewModel
import com.thanhtam.bookingtour.ui.search.SearchViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

//    ViewModel Factory is place to set all ViewModel of app in here
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel() as T
            modelClass.isAssignableFrom(BottomActivityViewModel::class.java) -> BottomActivityViewModel() as T
            else -> throw IllegalAccessException("ViewModelClass Not Found")
        }
    }
}