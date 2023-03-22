package com.seongju.composeroom.domain.user_case.upsert

import com.seongju.composeroom.common.util.Resource
import com.seongju.composeroom.domain.model.UserModel
import com.seongju.composeroom.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class UpsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(
        userName: String,
        userPassword: String,
        userBirthday: String,
        userPhoneNumber: String,
        createTime: LocalDate,
    ): Flow<Resource<Boolean>> = flow {
        if (userName.isBlank()){
            emit(Resource.Error<Boolean>(message = "이름을 입력해주세요."))
            return@flow
        }
        if (userPassword.isBlank()){
            emit(Resource.Error<Boolean>(message = "비밀번호를 입력해주세요."))
            return@flow
        }
        if (userBirthday.isBlank()) {
            emit(Resource.Error<Boolean>(message = "생년월일을 입력해주세요."))
            return@flow
        }
        if (userPhoneNumber.isBlank()){
            emit(Resource.Error<Boolean>(message = "휴대폰번호를 입력해주세요."))
            return@flow
        }
        try {
            val birthday = LocalDate.parse(userBirthday, DateTimeFormatter.BASIC_ISO_DATE)
            userRepository.upsertUser(
                UserModel(
                    userName = userName,
                    password = userPassword,
                    phoneNumber = userPhoneNumber,
                    birthday = birthday,
                    createTime = createTime
                )
            )
            emit(Resource.Success<Boolean>(data = true))
        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(message = "에러가 발생하였습니다. 입력값을 확인해주세요."))
            return@flow
        }
    }
}