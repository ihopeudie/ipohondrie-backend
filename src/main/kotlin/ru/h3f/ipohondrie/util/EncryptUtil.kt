package ru.h3f.ipohondrie.util

import org.mindrot.jbcrypt.BCrypt

class EncryptUtil {

    companion object {

        fun hashPassword(pwd: String): String {
            val salt = BCrypt.gensalt(12)
            return BCrypt.hashpw(pwd, salt)
        }

        fun verifyPassword(candidatePassword: String, hashedPassword: String): Boolean {
            return BCrypt.checkpw(candidatePassword, hashedPassword)
        }
    }
}
