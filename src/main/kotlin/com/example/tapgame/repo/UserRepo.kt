package org.example.com.example.tapgame.repo

import org.example.com.example.tapgame.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepo : JpaRepository<User, UUID>