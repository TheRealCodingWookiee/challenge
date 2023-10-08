package de.patronus.challenge.device.business

import de.patronus.challenge.device.persistence.DevicePersistenceService
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val devicePersistenceService: DevicePersistenceService
) {

    fun createDevice(device: Device): Device {
        return devicePersistenceService.createDevice(device)
    }
}
