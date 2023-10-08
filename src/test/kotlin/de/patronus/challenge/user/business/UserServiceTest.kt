package de.patronus.challenge.user.business

import de.patronus.challenge.user.persistence.UserPersistenceService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class UserServiceTest {
    @RelaxedMockK lateinit var userPersistenceService: UserPersistenceService
    @InjectMockKs lateinit var sut: UserService

    @Test
    fun `should pass to user persistence service`() {
        val user = createUser()

        sut.createUser(user)

        verify { userPersistenceService.createUser(user) }
    }

    private fun createUser() = User(UUID.randomUUID(),"Bruce", "Wayne", createAddress(), LocalDate.of(2000, 1, 1))
    private fun createAddress() = Address("Richstreet", "1", "Gotham", "12345")

}