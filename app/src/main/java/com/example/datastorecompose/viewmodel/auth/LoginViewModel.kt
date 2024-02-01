package com.example.datastorecompose.viewmodel.auth

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.datastorecompose.datastore.DataStoreRepo
import com.example.datastorecompose.navigation.Screen
import com.example.datastorecompose.utils.Helper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataRepository: DataStoreRepo,
) : ViewModel() {

    fun validateLoginCredentials(emailAddress: String, password: String): Pair<Boolean, String> {
        var result = Pair(true, "")
        if (TextUtils.isEmpty(emailAddress)) {
            result = Pair(false, "Please enter email address")
        } else if (!Helper.isValidEmail(emailAddress)) {
            result = Pair(false, "Please enter valid email address")
        } else if (TextUtils.isEmpty(password)) {
            result = Pair(false, "Please enter password")
        } else if (!Helper.isValidPassword(password)) {
            result = Pair(false, "Please enter valid password")
        }
        return result
    }
    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    fun validateEmail(email: String) {
        if (email.isEmpty()) {
            _emailError.value = "Email is required"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value = "Invalid email address"
        } else {
            _emailError.value = ""
        }
    }

    fun validatePassword(password: String) {
        if (password.isEmpty()) {
            _passwordError.value = "Password is required"
        } else if (password.length < 6) {
            _passwordError.value = "Password must be at least 6 characters"
        } else {
            _passwordError.value = ""
        }
    }

    fun navigateForgotPassword(navController: NavController) {
        navController.navigate(Screen.ForgotPassword.route)
    }

    fun navigateRegister(navController: NavController) {
        navController.navigate(Screen.RegisterScreen.route)
    }

    fun navigateHome(navController: NavController) {
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.LoginScreen.route) {
                inclusive = true
            }
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