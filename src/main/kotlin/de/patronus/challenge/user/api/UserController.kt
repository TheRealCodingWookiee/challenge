package de.patronus.challenge.user.api

import de.patronus.challenge.ChallengeApplication
import de.patronus.challenge.device.api.DeviceDTO
import de.patronus.challenge.device.api.DeviceDTOMapper
import de.patronus.challenge.user.business.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.HashMap


@RestController
@RequestMapping(ChallengeApplication.SERVER_URL_PATH_API + "/users")
class UserController(
    private val userService: UserService,
    private val userMapper: UserDTOMapper,
    private val deviceMapper: DeviceDTOMapper
) {

    @PostMapping
    fun createUser(
        @RequestBody @Valid userDto: UserDTO
    ): ResponseEntity<UserDTO> {
        val user = userMapper.toModel(userDto)
        val createdUser = userService.createUser(user)

        return ResponseEntity.status(CREATED).body(userMapper.toDto(createdUser))
    }

    @PutMapping("/{userId}/devices")
    fun assignDevice(
        @PathVariable userId: UUID,
        @RequestBody @Valid device: DeviceDTO
    ): ResponseEntity<UserDTO> {
        val device = deviceMapper.toModel(device)
        val updatedUser = userService.assignDevice(device, userId)

       return ResponseEntity.status(OK).body(userMapper.toDto(updatedUser))
    }

    @GetMapping("/devices")
    fun getAllUsersWithDevices(): ResponseEntity<UserListDTO> {
        val usersWithDevices = userService.getAllUsersWithDevices()
        return ResponseEntity.ok(userMapper.toUserListDTO(usersWithDevices))
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