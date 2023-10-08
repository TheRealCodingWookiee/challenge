package de.patronus.challenge.user.persistence

import de.patronus.challenge.user.business.User
import org.springframework.stereotype.Service

@Service
class UserPersistenceService(
    private val userRepository: UserRepository,
    private val userEntityMapper: UserEntityMapper
) {
    fun createUser(user: User): User {
        val eUser = userRepository.save(userEntityMapper.toEntity(user))

        return userEntityMapper.toModel(eUser)
    }
}