package com.seongju.composeroom.presentation.sign_up

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.seongju.composeroom.presentation.util.Screen
import com.seongju.composeroom.ui.theme.SpaceDefault
import com.seongju.composeroom.ui.theme.SpaceSmall
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "회원가입")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack(
                                route = Screen.LoginScreen.route,
                                inclusive = false
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                SignUpScreenBody(
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun SignUpScreenBody(
    navController: NavController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val tag = "SignUpScreenBody"
    val localContext = LocalContext.current
    val scrollState = rememberScrollState()

    val userName = signUpViewModel.userName.value
    val userPassword = signUpViewModel.userPassword.value
    val userBirthday = signUpViewModel.userBirthday.value
    val userPhoneNumber = signUpViewModel.userPhoneNumber.value

    val signUpState = signUpViewModel.signUpState

    LaunchedEffect(key1 = true) {
        signUpState.collect{ state ->
            if (state.error != null) {
                Toast.makeText(localContext, state.error, Toast.LENGTH_SHORT).show()
            }
            if (state.loading) {
                Log.d(tag, "로딩중")
            }
            if (state.success) {
                navController.popBackStack(
                    route = Screen.LoginScreen.route,
                    inclusive = false
                )
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
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "이름"
            )
            Spacer(
                modifier = Modifier
                    .height(SpaceSmall)
            )
            StandardTextField(
                value = userName,
                hint = "이름",
                onValueChange = {
                    signUpViewModel.setTextFiled(
                        userName = it
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(SpaceDefault)
            )
            Text(
                text = "비밀번호"
            )
            Spacer(
                modifier = Modifier
                    .height(SpaceSmall)
            )
            StandardTextField(
                value = userPassword,
                hint = "비밀번호",
                keyboardType = KeyboardType.NumberPassword,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    signUpViewModel.setTextFiled(
                        userPassword = it
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(SpaceDefault)
            )
            Text(
                text = "생년월일"
            )
            Spacer(
                modifier = Modifier
                    .height(SpaceSmall)
            )
            StandardTextField(
                value = userBirthday,
                hint = "생년월일(예.19970202)",
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    signUpViewModel.setTextFiled(
                        userBirthday = it
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(SpaceDefault)
            )
            Text(
                text = "휴대폰 번호"
            )
            StandardTextField(
                value = userPhoneNumber,
                hint = "휴대폰 번호(예.01012341234)",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Phone,
                onValueChange = {
                    signUpViewModel.setTextFiled(
                        userPhoneNumber = it
                    )
                }
            )
        }
        Spacer(
            modifier = Modifier
                .height(SpaceDefault)
        )
        StandardButton(
            buttonText = "회원가입"
        ) {
            signUpViewModel.upsertUser(
                userName = userName,
                userPassword = userPassword,
                userBirthday = userBirthday,
                userPhoneNumber = userPhoneNumber,
                createTime = LocalDate.now()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(

) {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SignUpScreen(
            navController = navController
        )
    }
}