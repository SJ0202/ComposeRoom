package com.seongju.composeroom.presentation.sign_up

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongju.composeroom.common.util.Resource
import com.seongju.composeroom.domain.user_case.upsert.UpsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val upsertUserUseCase: UpsertUserUseCase
): ViewModel() {

    private val tag = "SignUpViewModel"

    private val _userName: MutableState<String> = mutableStateOf("")
    val userName: MutableState<String> = _userName
    private val _userPassword: MutableState<String> = mutableStateOf("")
    val userPassword: MutableState<String> = _userPassword
    private val _userPhoneNumber: MutableState<String> = mutableStateOf("")
    val userPhoneNumber: MutableState<String> = _userPhoneNumber
    private val _userBirthday: MutableState<String> = mutableStateOf("")
    val userBirthday: MutableState<String> = _userBirthday

    private val _signUpState = MutableSharedFlow<SignUpState>()
    val signUpState = _signUpState.asSharedFlow()

    fun upsertUser(
        userName: String,
        userPassword: String,
        userBirthday: String,
        userPhoneNumber: String,
        createTime: LocalDate,
    ) {
        upsertUserUseCase.invoke(
            userName = userName,
            userPassword = userPassword,
            userBirthday = userBirthday,
            userPhoneNumber = userPhoneNumber,
            createTime = createTime
        ).onEach {
            when(it) {
                is Resource.Error -> {
                    _signUpState.emit(
                        SignUpState(
                            loading = false,
                            success = false,
                            error = it.message
                        )
                    )
                }
                is Resource.Loading -> {
                    _signUpState.emit(
                        SignUpState(
                            loading = true,
                            success = false,
                            error = null
                        )
                    )
                }
                is Resource.Success -> {
                    _signUpState.emit(
                        SignUpState(
                            loading = false,
                            success = true,
                            error = null
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setTextFiled(
        userName: String? = null,
        userPassword: String? = null,
        userPhoneNumber: String? = null,
        userBirthday: String? = null
    ) {
        if (userName != null) {
            _userName.value = userName.toString()
        }
        if (userPassword != null) {
            _userPassword.value = userPassword.toString()
        }
        if (userPhoneNumber != null) {
            _userPhoneNumber.value = userPhoneNumber.toString()
        }
        if (userBirthday != null) {
            _userBirthday.value = userBirthday.toString()
        }
    }
}