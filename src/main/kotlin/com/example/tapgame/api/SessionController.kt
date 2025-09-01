package org.example.com.example.tapgame.api

import jakarta.validation.Valid
import org.example.com.example.tapgame.domain.GameSession
import org.example.com.example.tapgame.domain.Score
import org.example.com.example.tapgame.dto.SessionEndRequest
import org.example.com.example.tapgame.dto.SessionStartRequest
import org.example.com.example.tapgame.repo.ScoreRepo
import org.example.com.example.tapgame.repo.SessionRepo
import org.example.com.example.tapgame.repo.UserRepo
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/sessions")
class SessionController(
    private val userRepo: UserRepo,
    private val sessionRepo: SessionRepo,
    private val scoreRepo: ScoreRepo
) {
    @PostMapping
    fun start(@Valid @RequestBody req: SessionStartRequest): Map<String, Any> {
        val user = userRepo.findById(UUID.fromString(req.userId)).orElseThrow()
        val sessionId = UUID.fromString(req.sessionId)
        val session = GameSession.start(user, sessionId, req.startedAt, req.deviceInfo)
        sessionRepo.save(session)
        return mapOf("success" to true, "data" to mapOf("sessionId" to session.id.toString()))
    }

    @PatchMapping("/{sessionId}/end")
    fun end(
        @PathVariable sessionId: String,
        @Valid @RequestBody req: SessionEndRequest
    ): Map<String, Any> {
        val session = sessionRepo.findById(UUID.fromString(sessionId)).orElseThrow()
        session.end(req.endedAt, req.finalScore)
        sessionRepo.save(session)
        scoreRepo.save(Score.of(session.user!!, session, session.finalScore!!)) // unique(session_id) 보장
        return mapOf("success" to true)
    }
}