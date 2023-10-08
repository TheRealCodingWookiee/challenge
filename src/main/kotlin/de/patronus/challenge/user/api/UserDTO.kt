package de.patronus.challenge.user.api

import java.time.LocalDate

data class UserDTO(
    val firstName: String,
    val lastName: String,
    val address: AddressDTO,
    val birthday: LocalDate
)

data class AddressDTO(
    val street: String,
    val houseNumber: String,
    val city: String,
    val postalCode: String
)
