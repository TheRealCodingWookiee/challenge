package de.patronus.challenge.device.persistence

import de.patronus.challenge.device.business.Device
import org.springframework.stereotype.Service

@Service
class DevicePersistenceService(
    private val deviceRepository: DeviceRepository,
    private val mapper: DeviceEntityMapper
) {

    fun createDevice(device: Device): Device {
        val createdDevice = deviceRepository.save(mapper.toEntity(device))

        return mapper.toModel(createdDevice)
    }
}
