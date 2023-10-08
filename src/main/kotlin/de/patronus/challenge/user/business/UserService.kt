package de.patronus.challenge.user.business

import de.patronus.challenge.user.persistence.UserPersistenceService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userPerstistenceService: UserPersistenceService
) {
    fun createUser(user: User): User {
        return userPerstistenceService.createUser(user)
    }
}
