package com.seongju.composeroom.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.seongju.composeroom.presentation.login.components.StandardButton
import com.seongju.composeroom.presentation.login.components.StandardTextField
import com.seongju.composeroom.ui.theme.SpaceDefault

@Composable
fun LoginScreen(
    navController: NavController
) {
    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LoginScreenBody(
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun LoginScreenBody(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val tag = "LoginScreenBody"
    val localContext = LocalContext.current
    val scrollState = rememberScrollState()

    val userName = loginViewModel.userName.value
    val userPassword = loginViewModel.userPassword.value
    val userState = loginViewModel.userState

    LaunchedEffect(true) {
        userState.collect { state ->
            if (state.error != null) {
                Toast.makeText(localContext, state.error, Toast.LENGTH_SHORT).show()
            }
            if (state.loading) {
                Log.d(tag, "로딩중")
            }
            if (state.user != null) {
                Log.d(tag, state.user.toString())
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceDefault)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StandardTextField(
                value = userName,
                hint = "이름",
                onValueChange = {
                    loginViewModel.setTextFiled(
                        userName = it
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(SpaceDefault)
            )
            StandardTextField(
                value = userPassword,
                hint = "비밀번호",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.NumberPassword,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    loginViewModel.setTextFiled(
                        userPassword = it
                    )
                }
            )
        }
        Spacer(
            modifier = Modifier
                .height(SpaceDefault)
        )
        StandardButton(
            buttonText = "로  그  인"
        ) {
            loginViewModel.getUser(
                userName = userName,
                userPassword = userPassword
            )
        }
        Spacer(
            modifier = Modifier
                .height(SpaceDefault)
        )
        StandardButton(
            buttonText = "회원가입",
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            loginViewModel.getUser(
                userName = userName,
                userPassword = userPassword
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(

) {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LoginScreen(
            navController = navController
        )
    }
}