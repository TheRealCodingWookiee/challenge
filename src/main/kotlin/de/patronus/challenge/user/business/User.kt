package de.patronus.challenge.user.business

import de.patronus.challenge.device.business.Device
import java.time.LocalDate
import java.util.*

data class User(
    val id: UUID?,
    val firstName: String,
    val lastName: String,
    val address: Address,
    val birthday: LocalDate,
    val devices: List<Device>
)

data class Address(
    val street: String,
    val houseNumber: String,
    val city: String,
    val postalCode: String
)
