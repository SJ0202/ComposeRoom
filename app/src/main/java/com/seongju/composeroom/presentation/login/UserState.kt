package com.seongju.composeroom.presentation.login

import com.seongju.composeroom.domain.model.UserModel

data class UserState(
    val loading: Boolean = false,
    val user: List<UserModel>? = null,
    val error: String? = null
)
