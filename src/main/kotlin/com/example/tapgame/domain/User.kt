package org.example.com.example.tapgame.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
class User (
    @Id
    val id: UUID? = null,

    @Column(nullable = false, length = 24)
    var nickname: String = "",

    @Column(nullable = false, length = 10)
    var provider: String = "guest",  // geust/google/apple

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant = Instant.now(),
){
    companion object{
        fun guest(nickname: String) =
            User(id = UUID.randomUUID(), nickname = nickname, provider = "guest", createdAt = Instant.now())
    }
}

