package com.example.datastorecompose.viewmodel.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.datastorecompose.datastore.DataStoreRepo
import com.example.datastorecompose.navigation.Screen
import com.example.datastorecompose.utils.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val dataRepository: DataStoreRepo
) : ViewModel() {
    val loading = mutableStateOf(false)
    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    fun validateEmail(email: String) {
        if (email.isEmpty()) {
            _emailError.value = "Email is required"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = "Invalid email address"
        } else {
            _emailError.value = ""
        }
    }

    fun validateCredentials(emailAddress: String): Pair<Boolean, String> {
        var result = Pair(true, "")
        if (TextUtils.isEmpty(emailAddress)) {
            result = Pair(false, "Please provide the credentials")
        } else if (!Helper.isValidEmail(emailAddress)) {
            result = Pair(false, "Email is invalid")
        }
        return result
    }

    fun navigateLogin(navController: NavController) {
        viewModelScope.launch {
            loading.value = true
            delay(2000)
            navController.navigate(route = Screen.LoginScreen.route)
            loading.value = false
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