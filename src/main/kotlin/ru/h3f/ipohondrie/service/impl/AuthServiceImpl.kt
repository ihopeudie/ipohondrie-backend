package ru.h3f.ipohondrie.service.impl

import org.springframework.stereotype.Service
import ru.h3f.ipohondrie.model.dto.UserRequestDto
import ru.h3f.ipohondrie.model.entity.User
import ru.h3f.ipohondrie.repository.UserRepository
import ru.h3f.ipohondrie.service.AuthService
import ru.h3f.ipohondrie.util.EncryptUtil
import mu.KotlinLogging
import ru.h3f.ipohondrie.exception.AuthException
import ru.h3f.ipohondrie.model.entity.AuthToken
import ru.h3f.ipohondrie.repository.AuthTokenRepository
import ru.h3f.ipohondrie.util.ValidationUtil
import java.util.*

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val authTokenRepository: AuthTokenRepository
) : AuthService {

    override fun registerUser(request: UserRequestDto) {
        log.info("request to save new user for request: $request")
        if (userRepository.existsByUserName(request.userName)) {
            throw AuthException.userAlreadyExists(request.userName)
        }
        if (userRepository.existsByEmail(request.email)) {
            throw AuthException.emailAlreadyUsed(request.email)
        }
        if (!ValidationUtil.isEmail(request.email)) {
            throw AuthException.emailIsInvalid(request.email)
        }
        val user = User(
            userName = request.userName,
            email = request.email ?: "",
            password = EncryptUtil.hashPassword(request.password)
        )
        val savedUser = userRepository.save(user)
        log.info("saved new user: $savedUser")
    }

    override fun getOrCreateToken(request: UserRequestDto): String {
        log.info("request to get token for user $request")
        val user =
            userRepository.findByUserName(request.userName) ?: throw AuthException.userNotExists()

        if (!EncryptUtil.verifyPassword(request.password, user.password)) {
            throw AuthException.wrongPassword()
        }

        //TODO delete if token is old and create new
        //TODO return existing token if it exists
        val savedToken = authTokenRepository.save(
            AuthToken(
                token = UUID.randomUUID().toString(),
                user = user
            )
        )
        log.info("saved token: $savedToken")
        return savedToken.token
    }

    override fun getUserInfo(token: String): User {
        return userRepository.getUserByToken(token) ?: throw AuthException.invalidToken()
    }

    companion object {
        val log = KotlinLogging.logger { }
    }
}

