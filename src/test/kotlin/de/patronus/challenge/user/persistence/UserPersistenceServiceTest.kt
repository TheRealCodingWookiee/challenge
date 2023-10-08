package de.patronus.challenge.user.persistence

import assertk.assertThat
import assertk.assertions.containsExactly
import de.patronus.challenge.user.api.UserDTO
import de.patronus.challenge.user.business.Address
import de.patronus.challenge.user.business.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import java.time.LocalDate
import java.util.*

@DataJpaTest(
    includeFilters = [ComponentScan.Filter(type = FilterType.REGEX, pattern = [".*MapperImpl"])]
)
@Import(UserPersistenceService::class)
class UserPersistenceServiceTest {
    @Autowired private lateinit var sut: UserPersistenceService
    @Autowired private lateinit var userRepository: UserRepository


    @Test
    fun `should save user`() {
        sut.createUser(createUser())

        assertThat(userRepository.findAll().map { it.firstName }).containsExactly("Bruce")
    }

    private fun createUser() = User(UUID.randomUUID(),"Bruce", "Wayne", createAddress(), LocalDate.of(2000, 1, 1))
    private fun createAddress() = Address("Richstreet", "1", "Gotham", "12345")
}