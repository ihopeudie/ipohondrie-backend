package ru.h3f.ipohondrie.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
class AuthToken (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val token: String,
    val dateGiven: Timestamp = Timestamp.valueOf(LocalDateTime.now()),
    val validUntil: Timestamp = Timestamp.valueOf(LocalDateTime.now().plusDays(3)),

    @OneToOne
    val user: User
)
