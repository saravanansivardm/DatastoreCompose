package com.example.datastorecompose.viewmodel.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.datastorecompose.datastore.DataStoreRepo
import com.example.datastorecompose.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val dataRepository: DataStoreRepo
) : ViewModel() {

    fun navigateHomeScreen(navController: NavController) {
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.Verification.route) {
                inclusive = true
            }
        }
    }

    fun navigateNeedHelp(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"))
        context.startActivity(intent)
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