package org.example.com.example.tapgame.api

import jakarta.validation.Valid
import org.example.com.example.tapgame.domain.User
import org.example.com.example.tapgame.repo.UserRepo
import org.example.com.example.tapgame.dto.GuestSignupRequest
import org.example.com.example.tapgame.dto.GuestSignupResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val userRepo: UserRepo) {

    @PostMapping("/guest")
    fun guest(@Valid @RequestBody req: GuestSignupRequest): Map<String, Any> {
        val user = User.guest(req.nickname)
        userRepo.save(user)
        return mapOf("success" to true, "data" to GuestSignupResponse(user.id!!.toString()))
    }
}