package com.example.datastorecompose.auth

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.datastorecompose.viewmodel.auth.VerificationViewModel
import com.example.datastorecompose.R
import com.example.datastorecompose.ui.theme.YellowColor

@Composable
fun VerificationScreen(
    navController: NavController,
    verificationViewModel: VerificationViewModel = hiltViewModel()
) {
    var isError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    fun validate(text: String) {
        isError = if (text.isEmpty()) {
            true
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
        }


    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 100.dp)
        ) {

            Image(
                imageVector = Icons.Default.AccountBox,
                modifier = Modifier.size(280.dp),
                contentDescription = ""
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.otp_sent_msg),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    maxLines = 2,
                    style = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Normal)
                )
            }
            Button(
                onClick = {
//                    validate()
//                    navController.navigate(FAV_FOOD_GRAPH_ROUTE) {
//                        popUpTo(AUTH_GRAPH_ROUTE)
//                    }
                    verificationViewModel.saveNamePref("firstname", "2")
                    verificationViewModel.navigateHomeScreen(navController)
                },
                shape = CutCornerShape(10),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(10.dp),
                colors = buttonColors(YellowColor),
            ) {
                Text(
                    text = stringResource(id = R.string.verification),
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
                    text = stringResource(id = R.string.dint_receive_code),
                    color = Color.Gray,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.resend),
                    color = Color.Black,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                val intent =
                    remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")) }
                Text(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 40.dp)
                        .clickable {
//                            context.startActivity(intent)
                            verificationViewModel.navigateNeedHelp(context)
                        },
                    text = stringResource(id = R.string.need_help),
                    color = Color.Black,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewVerificationScreen() {
    VerificationScreen(navController = rememberNavController())
}