package de.patronus.challenge.user.business

import de.patronus.challenge.user.UserFixture.createUser
import de.patronus.challenge.user.persistence.UserPersistenceService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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


}