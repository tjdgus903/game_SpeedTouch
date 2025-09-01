package org.example.com.example.tapgame.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.Instant

data class GuestSignupRequest(
    @field:NotBlank val nickname: String
)
data class GuestSignupResponse(val userId: String)

data class SessionStartRequest(
    @field:NotBlank val userId: String,
    @field:NotBlank val sessionId: String,
    @field:NotNull  val startedAt: Instant,
    val deviceInfo: String?
)
data class SessionEndRequest(
    @field:NotNull val finalScore: Int,
    @field:NotNull val endedAt: Instant
)

data class LeaderboardItem(
    val rank: Int, val nickname: String, val score: Int, val occurredAt: String
)