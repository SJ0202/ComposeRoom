package com.seongju.composeroom.presentation.sign_up

data class SignUpState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
)
