package de.patronus.challenge.user.api

import de.patronus.challenge.ChallengeApplication
import de.patronus.challenge.user.business.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ChallengeApplication.SERVER_URL_PATH_API + "/user")
class UserController(
    private val userService: UserService,
    private val mapper: UserDTOMapper
) {

    @PostMapping
    fun createUser(
        @RequestBody userDto: UserDTO
    ): ResponseEntity<UserDTO> {
        val user = mapper.toModel(userDto)
        val createdUser = userService.createUser(user)

        return ResponseEntity.status(CREATED).body(mapper.toDto(createdUser))
    }
}