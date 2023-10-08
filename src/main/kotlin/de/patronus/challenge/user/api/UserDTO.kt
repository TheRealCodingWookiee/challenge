package de.patronus.challenge.user.api

import java.time.LocalDate
import jakarta.validation.constraints.NotEmpty


data class UserDTO(
    @field:NotEmpty(message = "NO_FIRST_NAME_PROVIDED")
    val firstName: String?,
    @field:NotEmpty(message = "NO_LAST_NAME_PROVIDED")
    val lastName: String?,
    val address: AddressDTO?,
    val birthday: LocalDate?
)

data class AddressDTO(
    val street: String?,
    val houseNumber: String?,
    val city: String?,
    val postalCode: String?
)
