package com.example.datastorecompose.utils

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager


class Helper {
    companion object {
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }


        fun isValidPassword(password: String): Boolean {
            if (password.length < 5) return false
           /* if (password.firstOrNull { it.isDigit() } == null) return false
            if (password.filter { it.isLetter() }
                    .firstOrNull { it.isUpperCase() } == null) return false
            if (password.filter { it.isLetter() }
                    .firstOrNull { it.isLowerCase() } == null) return false
            if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false*/
            return true
        }

        fun hideKeyboard(view: View) {
            try {
                val imm =
                    view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun showKeyboard(view: View) {
            try {
                (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}