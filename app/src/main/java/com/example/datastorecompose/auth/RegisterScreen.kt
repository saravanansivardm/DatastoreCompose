package com.example.datastorecompose.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import com.example.datastorecompose.viewmodel.auth.RegisterViewModel
import com.example.datastorecompose.R
import com.example.datastorecompose.ui.theme.YellowColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val fullNameError by registerViewModel.fullNameError.observeAsState("")
    val emailError by registerViewModel.emailError.observeAsState("")
    val passwordError by registerViewModel.passwordError.observeAsState("")
    val phoneNumberError by registerViewModel.phoneNumberError.observeAsState("")
    val fullNameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val phoneNumberFocusRequester = remember { FocusRequester() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.register_new_acc),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 40.dp),
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium)
            )

            TextField(
                isError = fullNameError.isNotEmpty(),
                supportingText = {
                    if (fullNameError.isNotEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = fullNameError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                value = fullName,
                onValueChange = {
                    fullName = it
                    registerViewModel.validateFullName(fullName)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(fullNameFocusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onNext = { emailFocusRequester.requestFocus() }
                ),
                label = { Text(text = stringResource(id = R.string.full_name)) },
                placeholder = { Text(text = stringResource(id = R.string.full_name)) },
                trailingIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Clear"
                        )
                    }
                }
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
                    registerViewModel.validateEmail(email)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
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
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Clear"
                        )
                    }
                }
            )
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
                    registerViewModel.validatePassword(password)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(passwordFocusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onNext = { phoneNumberFocusRequester.requestFocus() }
                ),
                label = { Text(text = stringResource(id = R.string.password)) },
                placeholder = { Text(text = stringResource(id = R.string.password)) },
                trailingIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Clear"
                        )
                    }
                }
            )

            TextField(
                isError = phoneNumberError.isNotEmpty(),
                supportingText = {
                    if (phoneNumberError.isNotEmpty()) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = phoneNumberError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                value = phone,
                onValueChange = {
                    phone = it
                    registerViewModel.validatePhoneNumber(phone)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(phoneNumberFocusRequester),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Phone
                ),
                keyboardActions = KeyboardActions(
                ),
                label = { Text(text = stringResource(id = R.string.phone)) },
                placeholder = { Text(text = stringResource(id = R.string.phone)) },
                trailingIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Clear"
                        )
                    }
                }
            )
            Button(
                onClick = {
                    if (fullNameError.isNotEmpty()) {
                        fullNameFocusRequester.requestFocus()
                    }
                    registerViewModel.validateFullName(fullName)
                    if (fullNameError.isEmpty()) {
                        emailFocusRequester.requestFocus()
                        registerViewModel.validateEmail(email)
                        if (emailError.isEmpty()) {
                            passwordFocusRequester.requestFocus()
                            registerViewModel.validatePassword(password)
                            if (passwordError.isEmpty()) {
                                phoneNumberFocusRequester.requestFocus()
                                registerViewModel.validatePhoneNumber(phone)
                                if (fullNameError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty() && phoneNumberError.isEmpty()) {
                                    registerViewModel.saveNamePref("firstname", "1")
                                    registerViewModel.navigateVerification(navController)
                                }
                            }
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
                    text = stringResource(id = R.string.register),
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
                    text = stringResource(id = R.string.login),
                    color = Color.Black,
                    modifier = Modifier.clickable {
//                        navController.navigate(route = Screen.Login.route)
                        registerViewModel.navigateLogin(navController)
                    },
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(navController = rememberNavController())
}