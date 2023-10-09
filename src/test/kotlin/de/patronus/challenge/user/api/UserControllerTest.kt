package de.patronus.challenge.user.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.patronus.challenge.device.api.DeviceDTOMapperImpl
import de.patronus.challenge.user.UserFixture.createUser
import de.patronus.challenge.user.UserFixture.createUserDTO
import de.patronus.challenge.user.business.UserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus.CREATED

@ExtendWith(MockKExtension::class)
class UserControllerTest{
    @RelaxedMockK lateinit var userService: UserService
    @SpyK var dtoMapper = UserDTOMapperImpl(DeviceDTOMapperImpl())
    @InjectMockKs lateinit var sut: UserController


    @Test
    fun `should create user and return dto and created status`() {
        val expectedUser = createUser()
        val expectedUserDTO = expectedUser.let(dtoMapper::toDto)

        every { userService.createUser(any()) } returns expectedUser

        val response = sut.createUser(createUserDTO())

        assertThat(response.statusCode).isEqualTo(CREATED)
        assertThat(response.body).isEqualTo(expectedUserDTO)
    }
}