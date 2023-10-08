package de.patronus.challenge.user.api

import de.patronus.challenge.ChallengeApplication
import de.patronus.challenge.user.business.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(ChallengeApplication.SERVER_URL_PATH_API + "/users")
class UserController(
    private val userService: UserService,
    private val mapper: UserDTOMapper
) {

    @PostMapping
    fun createUser(
        @RequestBody @Valid userDto: UserDTO
    ): ResponseEntity<UserDTO> {
        val user = mapper.toModel(userDto)
        val createdUser = userService.createUser(user)

        return ResponseEntity.status(CREATED).body(mapper.toDto(createdUser))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleConstraintViolationException(e: MethodArgumentNotValidException): ResponseEntity<HashMap<String, String?>> {
        val errors = HashMap<String, String?>()
        e.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage: String? = error.defaultMessage
            errors[fieldName] = errorMessage
        }
        return ResponseEntity.status(BAD_REQUEST).body(errors)
    }
}