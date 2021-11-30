package com.example.retrofitsampleapp.presentation

import androidx.activity.viewModels
import com.example.retrofitsampleapp.data.utils.Resource
import com.example.retrofitsampleapp.databinding.ActivityMainBinding
import com.example.retrofitsampleapp.presentation.base.BaseVBActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity :
    BaseVBActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    override fun viewModelSetup() {
        mainViewModel.response.observe(this) { response ->
            Timber.d(">>>>>>>> %s", response.status)
            Timber.d(">>>>>>>> %s", response.message)
            when (response.status) {
                Resource.Status.LOADING -> {
                    // bind data to the view
                }
                Resource.Status.ERROR -> {
                    // show error message
                }
                Resource.Status.SUCCESS -> {
                    // show a progress bar
                }
            }
        }
    }

    override fun viewSetup() {
        binding?.textview?.text = "Hey There"
        mainViewModel.getPosts()

    }

}