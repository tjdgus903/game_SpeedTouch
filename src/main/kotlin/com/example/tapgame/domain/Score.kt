package org.example.com.example.tapgame.domain

import jakarta.persistence.*
import java.time.Instant


@Entity
@Table(name = "scores")
class Score(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", unique = true)
    var session: GameSession? = null,

    @Column(nullable = false)
    var score: Int = 0,

    @Column(name = "occurred_at", nullable = false)
    var occurredAt: Instant = Instant.now()
) {
    companion object {
        fun of(user: User, session: GameSession, score: Int) =
            Score(user = user, session = session, score = score, occurredAt = Instant.now())
    }
}