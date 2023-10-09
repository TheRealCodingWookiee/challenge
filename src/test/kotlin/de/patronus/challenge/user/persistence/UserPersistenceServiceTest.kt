package de.patronus.challenge.user.persistence

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotNull
import de.patronus.challenge.device.DeviceFixture.createDevice
import de.patronus.challenge.user.UserFixture
import de.patronus.challenge.user.UserFixture.FIRST_NAME
import de.patronus.challenge.user.UserFixture.ID
import de.patronus.challenge.user.UserFixture.createUser
import de.patronus.challenge.user.api.UserDTO
import de.patronus.challenge.user.business.Address
import de.patronus.challenge.user.business.User
import de.patronus.challenge.user.business.UserNotFound
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

        assertThat(userRepository.findAll().map { it.firstName }).containsExactly(FIRST_NAME)
    }

    @Test
    fun `should assign device to user`() {
        val device = createDevice()
        val eUser = sut.createUser(createUser())

        val result = sut.assignDeviceToUser(device, eUser.id!!)

        assertThat(result.devices?.first()).isNotNull()
    }

    @Test
    fun `should throw error if no user found`() {
        val device = createDevice()

       assertFailure { sut.assignDeviceToUser(device, ID) }.isInstanceOf(UserNotFound::class)
    }

}