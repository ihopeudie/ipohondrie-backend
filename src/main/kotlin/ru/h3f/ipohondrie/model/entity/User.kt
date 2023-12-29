package ru.h3f.ipohondrie.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "`user`" )
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    val userName: String,
    @Column(unique = true, nullable = true)
    val email: String?,
    val password: String


) {
    override fun toString(): String {
        return "User(id=$id, userName='$userName', email='$email', password='$password')"
    }
}
