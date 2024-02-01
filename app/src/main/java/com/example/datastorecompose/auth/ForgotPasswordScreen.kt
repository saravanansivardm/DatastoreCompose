package com.example.datastorecompose.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
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
import com.example.datastorecompose.viewmodel.auth.ForgotPasswordViewModel
import com.example.datastorecompose.R
import com.example.datastorecompose.ui.theme.YellowColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val validationResult = validateUserInput(email, forgotPasswordViewModel)
    val emailError by forgotPasswordViewModel.emailError.observeAsState("")
    val emailFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password),
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
                    forgotPasswordViewModel.validateEmail(email)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .focusRequester(emailFocusRequester),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Black,
                    unfocusedBorderColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
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

            Text(
                text = if (isError) "" else stringResource(id = R.string.dont_share_info),
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                maxLines = 2,
                style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Normal)
            )
            Button(
                onClick = {
                    if (emailError.isNotEmpty()) {
                        emailFocusRequester.requestFocus()
                    }
                    forgotPasswordViewModel.validateEmail(email)
                    if (emailError.isEmpty()) {
                        Toast.makeText(context, "Yeaaa Successss", Toast.LENGTH_SHORT).show()
                        forgotPasswordViewModel.saveNamePref("firstname", "4")
                        forgotPasswordViewModel.navigateLogin(navController)
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
                    text = stringResource(id = R.string.reset_password),
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.remember_now),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.login_here),
                    color = Color.Black,
                    modifier = Modifier.clickable {
                        forgotPasswordViewModel.navigateLogin(navController)
                    },
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                val intent =
                    remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")) }
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .clickable {
                            coroutineScope.launch(Dispatchers.Main) {
                                delay(15000)
                            }
                            forgotPasswordViewModel.navigateNeedHelp(context)
                        },
                    text = stringResource(id = R.string.need_help),
                    color = Color.Black,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

fun showValidationErrors(error: String, context: Context) {
    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
}

fun validateUserInput(
    email: String,
    forgotPasswordViewModel: ForgotPasswordViewModel
): Pair<Boolean, String> {
    return forgotPasswordViewModel.validateCredentials(email)
}


@Preview(showBackground = true)
@Composable
fun PreviewForgotPasswordScreen() {
    ForgotPasswordScreen(navController = rememberNavController())
}