package de.patronus.challenge.device.business

import de.patronus.challenge.device.persistence.DevicePersistenceService
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeviceService(
    private val devicePersistenceService: DevicePersistenceService
) {

    fun createDevice(device: Device): Device {
        return devicePersistenceService.createDevice(device)
    }

    fun validateDeviceAssignment(deviceId: UUID): Boolean {
        if (devicePersistenceService.getOneDevice(deviceId).userId != null) {
            throw DeviceAlreadyAssigned()
        }

        return true
    }
}
