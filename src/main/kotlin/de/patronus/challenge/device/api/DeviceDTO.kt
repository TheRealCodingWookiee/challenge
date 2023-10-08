package de.patronus.challenge.device.api

import jakarta.validation.constraints.NotEmpty
import java.util.*

data class DeviceDTO(
    val id: UUID?,
    @field:NotEmpty(message = "NO_SERIAL_NUMBER_PROVIDED")
    val serialNumber: String,
    val model: String?,
    val phoneNumber: String?
)
