package org.example.com.example.tapgame.repo

import org.example.com.example.tapgame.domain.Score
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ScoreRepo : JpaRepository<Score, Long> {
    @Query("select s from Score s order by s.score desc, s.occurredAt desc")
    fun topAll(pageable: Pageable): Page<Score>
}