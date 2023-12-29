package ru.h3f.ipohondrie.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.h3f.ipohondrie.model.entity.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserName(userName: String): User?
    fun existsByUserName(userName: String): Boolean
    fun existsByEmail(email: String?): Boolean

    @Query(
        nativeQuery = true, value = """
        select * from "user"
        where id = (select user_id from auth_token where token = :token and 
        valid_until < now())
    """
    )
    fun getUserByToken(token: String): User?
}
