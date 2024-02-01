package com.example.datastorecompose.viewmodel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class RegisterViewModel @Inject constructor(
    private val dataRepository: DataStoreRepo
) : ViewModel() {

    private val _fullNameError = MutableLiveData<String>()
    val fullNameError: LiveData<String> = _fullNameError

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError

    private val _phoneNumberError = MutableLiveData<String>()
    val phoneNumberError: LiveData<String> = _phoneNumberError

    fun validateFullName(fullName: String) {
        if (fullName.isEmpty()) {
            _fullNameError.value = "Name is required"
        } else if (fullName.length < 6) {
            _fullNameError.value = "Name must be at least 6 characters"
        } else {
            _fullNameError.value = ""
        }
    }

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

    fun validatePhoneNumber(phoneNumber: String) {
        if (phoneNumber.isEmpty()) {
            _phoneNumberError.value = "Phone Number is required"
        } else if (phoneNumber.length != 10) {
            _phoneNumberError.value = "Phone Number must be 10 digits"
        } else {
            _phoneNumberError.value = ""
        }
    }

    fun navigateVerification(navController: NavController) {
        navController.navigate(route = Screen.Verification.route)
    }

    fun navigateLogin(navController: NavController) {
        navController.navigate(route = Screen.LoginScreen.route)
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