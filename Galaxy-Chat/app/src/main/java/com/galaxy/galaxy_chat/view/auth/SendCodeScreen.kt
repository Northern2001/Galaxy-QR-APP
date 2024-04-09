package com.galaxy.galaxy_chat.view.auth

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.galaxy.galaxy_chat.helper.AuthHelper
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendCodeScreen() {
    var phoneValue by remember { mutableStateOf("") }
    var timeCountDown by remember { mutableStateOf(0L) }
    var isSend by remember { mutableStateOf(false) }
    val activity = LocalContext.current as Activity


    LaunchedEffect(key1 = timeCountDown) {
        if (timeCountDown > 0) {
            delay(1000L)
            timeCountDown -= 1000L
        } else {
            isSend = false
        }
    }

    fun getTimeCountDown(): String {
        return if (isSend && timeCountDown != 0L) (timeCountDown / 1000).toString() else ""
    }

    fun senCode() {
        if (!isSend) {
            AuthHelper().sendVerificationCode(phoneValue, activity)
            timeCountDown = 60000L
            isSend = true
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column {
            OutlinedTextField(
                value = phoneValue,
                onValueChange = { phoneValue = it },
                label = { Text("Phone") }
            )
            Text(
                text = "Resend code? ${getTimeCountDown()}",
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 20.dp)
                    .clickable {

                        senCode()
                    },
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                color = Color.Blue
            )
        }
        OutlinedButton(
            onClick = {
                senCode()
            }) {
            Text("Send code")
        }

    }
}