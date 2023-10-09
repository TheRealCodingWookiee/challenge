package de.patronus.challenge.user.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<EUser, UUID> {
    fun findAllByDevicesIsNotNull(): List<EUser>
}