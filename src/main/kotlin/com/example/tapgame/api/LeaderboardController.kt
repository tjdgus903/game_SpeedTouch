package org.example.com.example.tapgame.api

import org.example.com.example.tapgame.domain.Score
import org.example.com.example.tapgame.dto.LeaderboardItem
import org.example.com.example.tapgame.repo.ScoreRepo
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/leaderboards")
class LeaderboardController(private val scoreRepo: ScoreRepo) {

    @GetMapping
    fun top(
        @RequestParam(defaultValue = "all") period: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "50") size: Int
    ): Map<String, Any> {
        val p = scoreRepo.topAll(PageRequest.of(page, size))
        val base = page * size
        val items = p.content.mapIndexed { i, s: Score ->
            LeaderboardItem(base + i + 1, s.user!!.nickname, s.score, s.occurredAt.toString())
        }
        return mapOf("success" to true, "data" to mapOf(
            "period" to period, "items" to items, "page" to page, "size" to size, "hasNext" to p.hasNext()
        ))
    }
}