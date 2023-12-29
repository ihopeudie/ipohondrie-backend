package ru.h3f.ipohondrie.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class AuthException(msg: String) : RuntimeException(msg) {
    companion object {
        fun userAlreadyExists(userName: String) = AuthException("User $userName already exists")
        fun emailAlreadyUsed(email: String?) = AuthException("Email $email is already used")
        fun userNotExists() = AuthException("User does not exist or password is incorrect")
        fun wrongPassword() = AuthException("User does not exist or password is incorrect")
        fun emailIsInvalid(email: String?) = AuthException("Email $email has incorrect format")
        fun invalidToken() = AuthException("Token is invalid")
    }
}
