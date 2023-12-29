package ru.h3f.ipohondrie.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.h3f.ipohondrie.model.entity.AuthToken

interface AuthTokenRepository: JpaRepository<AuthToken, Long> {
}
