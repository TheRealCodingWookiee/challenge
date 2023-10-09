package de.patronus.challenge.device.business

import de.patronus.challenge.common.ProblemException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.NOT_FOUND


abstract class DeviceException(code: String, status: HttpStatus):
    ProblemException(
        title = "User Exception: $code",
        status = status,
        error = code,
    )

class DeviceNotFound : DeviceException("DEVICE_NOT_FOUND", NOT_FOUND)

class DeviceAlreadyAssigned : DeviceException("DEVICE_ALREADY_ASSIGNED", CONFLICT)

class DeviceWithSerialNumberExists : DeviceException("SERIAL_NUMBER_ALREADY_EXISTS", CONFLICT)