package de.patronus.challenge.user.api

import de.patronus.challenge.device.api.DeviceDTO
import java.time.LocalDate
import jakarta.validation.constraints.NotEmpty
import java.util.*


data class UserDTO(
    val id: UUID?,
    @field:NotEmpty(message = "NO_FIRST_NAME_PROVIDED")
    val firstName: String?,
    @field:NotEmpty(message = "NO_LAST_NAME_PROVIDED")
    val lastName: String?,
    val address: AddressDTO?,
    val birthday: LocalDate?,
    val devices: List<DeviceDTO>?
)

data class AddressDTO(
    val street: String?,
    val houseNumber: String?,
    val city: String?,
    val postalCode: String?
)
