package de.patronus.challenge.user.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.patronus.challenge.user.business.Address
import de.patronus.challenge.user.business.User
import de.patronus.challenge.user.business.UserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus.CREATED
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class UserControllerTest{
    @RelaxedMockK lateinit var userService: UserService
    @SpyK var dtoMapper = UserDTOMapperImpl()
    @InjectMockKs lateinit var sut: UserController


    @Test
    fun `should create user and return dto and created status`() {
        val expectedUser = createUser()
        val expectedUserDTO = expectedUser.let(dtoMapper::toDto)

        every { userService.createUser(any()) } returns expectedUser

        val response = sut.createUser(createUserDto())

        assertThat(response.statusCode).isEqualTo(CREATED)
        assertThat(response.body).isEqualTo(expectedUserDTO)
    }


    private fun createUser() = User(UUID.randomUUID(),"Bruce", "Wayne", createAddress(), LocalDate.of(2000, 1, 1))
    private fun createUserDto() = UserDTO("Bruce", "Wayne", createAddressDto(), LocalDate.of(2000, 1, 1))

    private fun createAddress() = Address("Richstreet", "1", "Gotham", "12345")
    private fun createAddressDto() = AddressDTO("Richstreet", "1", "Gotham", "12345")

}