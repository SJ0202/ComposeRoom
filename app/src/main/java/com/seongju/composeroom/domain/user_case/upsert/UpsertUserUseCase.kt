package com.seongju.composeroom.domain.user_case.upsert

import com.seongju.composeroom.common.util.Resource
import com.seongju.composeroom.domain.model.UserModel
import com.seongju.composeroom.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(userModel: UserModel): Flow<Resource<Boolean>> = flow {
        if (userModel.userName.isBlank()){
            emit(Resource.Error<Boolean>(message = "이름을 입력해주세요."))
            return@flow
        }
        if (userModel.password.isBlank()){
            emit(Resource.Error<Boolean>(message = "비밀번호를 입력해주세요."))
            return@flow
        }
        if (userModel.phoneNumber.isBlank()){
            emit(Resource.Error<Boolean>(message = "휴대폰번호를 입력해주세요."))
            return@flow
        }
        userRepository.upsertUser(userModel = userModel)
        emit(Resource.Success<Boolean>(data = true))
    }

}