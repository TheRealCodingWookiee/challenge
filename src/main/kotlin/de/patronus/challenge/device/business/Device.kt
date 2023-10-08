package de.patronus.challenge.device.business

import java.util.*

data class Device(
    val id: UUID?,
    val serialNumber: String,
    val model: String?,
    val phoneNumber: String?
)