package org.example.com.example.tapgame.repo

import org.example.com.example.tapgame.domain.GameSession
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface SessionRepo : JpaRepository<GameSession, UUID>