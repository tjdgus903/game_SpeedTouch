package org.example.com.example.tapgame.domain

import jakarta.persistence.*
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "sessions")
class GameSession(
    @Id
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @Column(name = "start_at", nullable = false)
    var startAt: Instant? = null,

    @Column(name = "end_at")
    var endAt: Instant? = null,

    @Column(name = "final_score")
    var finalScore: Int? = null,

    @Column(name = "device_info")
    var deviceInfo: String? = null,

    @Column(name = "created_at", nullable = false)
    var createdAt: Instant = Instant.now()
) {
    fun end(endedAt:Instant, score: Int){
        this.endAt = endedAt
        this.finalScore = score
    }

    companion object{
        fun start(user: User, sessionId: UUID, startedAt: Instant, deviceInfo: String?) =
            GameSession(
                id = sessionId, user = user, startAt = startedAt,
                deviceInfo = deviceInfo, createdAt = Instant.now()
            )
    }
}