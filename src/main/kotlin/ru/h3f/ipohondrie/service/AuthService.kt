package ru.h3f.ipohondrie.service

import ru.h3f.ipohondrie.model.dto.UserRequestDto
import ru.h3f.ipohondrie.model.entity.User

interface AuthService {
    fun registerUser(request: UserRequestDto)
    fun getOrCreateToken(request: UserRequestDto): String
    fun getUserInfo(token: String): User?
}
