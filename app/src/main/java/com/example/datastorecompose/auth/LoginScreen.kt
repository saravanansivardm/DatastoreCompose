package com.example.datastorecompose.auth

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.datastorecompose.R
import com.example.datastorecompose.ui.theme.YellowColor
import com.example.datastorecompose.viewmodel.auth.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onClick: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val emailError by loginViewModel.emailError.observeAsState("")
    val passwordError by loginViewModel.passwordError.observeAsState("")
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        //        systemUiController.setSystemBarsColor(Color.White)
        systemUiController.setStatusBarColor(Color.White)
        systemUiController.setNavigationBarColor(Color.White)

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_to_find_best_food),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 40.dp),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium)
            )
            TextField(
                isError = emailError.isNotEmpty(),
                supportingText = {
                    if (emailError.isNotEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = emailError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },

                value = email,
                onValueChange = {
                    email = it
                    loginViewModel.validateEmail(email)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black, unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(emailFocusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                ),
                label = { Text(text = stringResource(id = R.string.email)) },
                placeholder = { Text(text = stringResource(id = R.string.email)) },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Email, contentDescription = "Clear"
                        )
                    }
                })

            TextField(
                isError = passwordError.isNotEmpty(),
                supportingText = {
                    if (passwordError.isNotEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = passwordError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                value = password,
                onValueChange = {
                    password = it
                    loginViewModel.validatePassword(password)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black, unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(passwordFocusRequester)
                    .background(Color.Transparent),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                ),
                label = { Text(text = stringResource(id = R.string.password)) },
                placeholder = { Text(text = stringResource(id = R.string.password)) },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Lock, contentDescription = "Clear"
                        )
                    }
                })

            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.forgot_pwd),
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clickable {
                            loginViewModel.navigateForgotPassword(navController)
                        }
                        .padding(16.dp),
                    maxLines = 2,
                    style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Normal))
            }
            Button(
                onClick = {
                    if (emailError.isNotEmpty()) {
                        emailFocusRequester.requestFocus()
                    }
                    loginViewModel.validateEmail(email)
                    if (emailError.isEmpty()) {
                        passwordFocusRequester.requestFocus()
                        loginViewModel.validatePassword(password)
                        if (emailError.isEmpty() && passwordError.isEmpty()) {
                            Toast.makeText(context, "Yeaaa Successss", Toast.LENGTH_SHORT).show()
                            loginViewModel.saveNamePref("firstname", "4")
                            loginViewModel.navigateHome(navController)
                        }
                    }
                },
                shape = CutCornerShape(10),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp),
                colors = buttonColors(YellowColor),
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
            }
            Button(
                onClick = {
                },
                shape = CutCornerShape(10),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp),
                colors = buttonColors(Color.White),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Row {
                    Image(
                        imageVector = Icons.Default.AccountBox, contentDescription = ""
                    )
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = stringResource(id = R.string.sign_up_fb),
                        color = Color.Black,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.dont_have_acc),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.register),
                    color = Color.Black,
                    modifier = Modifier.clickable {
                        loginViewModel.navigateRegister(navController)
                    },
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController(), onClick = {})
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun PreviewDarkLoginScreen() {
    LoginScreen(navController = rememberNavController(), onClick = {})
}