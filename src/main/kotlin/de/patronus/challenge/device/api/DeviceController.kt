package de.patronus.challenge.device.api

import de.patronus.challenge.ChallengeApplication
import de.patronus.challenge.device.business.DeviceService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ChallengeApplication.SERVER_URL_PATH_API + "/devices")
class DeviceController(
    private val deviceService: DeviceService,
    private val mapper: DeviceDTOMapper
) {

    @PostMapping
    fun createDevice(
        @RequestBody @Valid deviceDto: DeviceDTO
    ): ResponseEntity<DeviceDTO> {
        val device = mapper.toModel(deviceDto)
        val createdDevice = deviceService.createDevice(device)

        return ResponseEntity.status(CREATED).body(mapper.toDTO(createdDevice))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(e: MethodArgumentNotValidException): ResponseEntity<HashMap<String, String?>> {
        val errors = HashMap<String, String?>()
        e.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage: String? = error.defaultMessage
            errors[fieldName] = errorMessage
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }
}