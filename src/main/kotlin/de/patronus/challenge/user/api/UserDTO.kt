package de.patronus.challenge.user.api

import java.util.Date

data class UserDTO(
    val firstName: String,
    val lastName: String,
    val address: AddressDTO,
    val birthday: Date
)

data class AddressDTO(
    val street: String,
    val houseNumber: String,
    val city: String,
    val postalCode: String
)
