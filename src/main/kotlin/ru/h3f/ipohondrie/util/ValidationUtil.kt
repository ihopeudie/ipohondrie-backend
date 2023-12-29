package ru.h3f.ipohondrie.util

const val EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

class ValidationUtil {
    companion object {
        fun isEmail(email: String?): Boolean {
            if (email.isNullOrBlank()) {
                return false
            }
            return email.matches(Regex(EMAIL_REGEX))
        }
    }
}
