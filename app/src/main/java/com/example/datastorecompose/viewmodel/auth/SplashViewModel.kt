package com.example.datastorecompose.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastorecompose.datastore.DataStoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataRepository: DataStoreRepo
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(500)
            _isLoading.value = false
        }
    }

    fun saveNamePref(key: String, value: String) {
        viewModelScope.launch {
            dataRepository.putString(key, value)
        }
    }

    fun getNamePref(): String? = runBlocking {
        dataRepository.getString("firstname")
    }

}