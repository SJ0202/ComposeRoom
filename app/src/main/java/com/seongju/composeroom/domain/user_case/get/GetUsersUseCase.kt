package com.seongju.composeroom.domain.user_case.get

import com.seongju.composeroom.common.util.Resource
import com.seongju.composeroom.domain.model.UserModel
import com.seongju.composeroom.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(userName:String, password: String): Flow<Resource<List<UserModel>>> = flow {
        try {
            emit(Resource.Loading<List<UserModel>>())
            if (userName.isBlank()) {
                emit(Resource.Error<List<UserModel>>(message = "이름을 입력해주세요."))
                return@flow
            }
            if (password.isBlank()) {
                emit(Resource.Error<List<UserModel>>(message = "비밀번호를 입력해주세요."))
                return@flow
            }
            val result = userRepository.getUser(
                userName = userName,
                password = password
            )
            emit(Resource.Success<List<UserModel>>(data = result))
        } catch (e: IOException) {
            emit(Resource.Error<List<UserModel>>(message = "데이터가 없습니다."))
        }
    }

}