package com.seongju.composeroom.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongju.composeroom.common.util.Resource
import com.seongju.composeroom.domain.user_case.get.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _userName: MutableState<String> = mutableStateOf("")
    val userName: MutableState<String> = _userName
    private val _userPassword: MutableState<String> = mutableStateOf("")
    val userPassword: MutableState<String> = _userPassword
    private val _userState = MutableSharedFlow<UserState>()
    val userState = _userState.asSharedFlow()

    fun getUser(userName: String, userPassword: String) {
        getUsersUseCase.invoke(userName = userName, password = userPassword).onEach {
            when(it) {
                is Resource.Error -> {
                    _userState.emit(
                        UserState(
                            loading = false,
                            user = null,
                            error = it.message
                        )
                    )
                }
                is Resource.Loading -> {
                    _userState.emit(
                        UserState(
                            loading = true,
                            user = null,
                            error = null
                        )
                    )
                }
                is Resource.Success -> {
                    _userState.emit(
                        UserState(
                            loading = false,
                            user = it.data,
                            error = null
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setTextFiled(
        userName: String? = null,
        userPassword: String? = null
    ) {
        if (!userName.isNullOrBlank()) {
            _userName.value = userName.toString()
        }
        if (!userPassword.isNullOrBlank()) {
            _userPassword.value = userPassword.toString()
        }
    }
}