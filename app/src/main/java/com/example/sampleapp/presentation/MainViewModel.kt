package com.example.sampleapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.data.remote.model.GetPostResponse
import com.example.sampleapp.data.repository.AppRepository
import com.example.sampleapp.data.utils.Resource
import com.example.sampleapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AppRepository
) : BaseViewModel() {

    private val _response: MutableLiveData<Resource<GetPostResponse>> = MutableLiveData()
    val response: LiveData<Resource<GetPostResponse>> = _response

    /**
     * For more details regarding flow :
     * 1) https://developer.android.com/kotlin/flow
     * 2) https://kotlinlang.org/docs/flow.html#flattening-flows
     */
    fun getPosts() = viewModelScope.launch {
        repository.getPost().collect { response ->
            _response.postValue(response)
        }
    }
}