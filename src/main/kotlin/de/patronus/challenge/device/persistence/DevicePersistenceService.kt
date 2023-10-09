package de.patronus.challenge.device.persistence

import de.patronus.challenge.device.business.Device
import de.patronus.challenge.device.business.DeviceNotFound
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DevicePersistenceService(
    private val deviceRepository: DeviceRepository,
    private val mapper: DeviceEntityMapper
) {

    fun createDevice(device: Device): Device {
        val createdDevice = deviceRepository.save(mapper.toEntity(device))

        return mapper.toModel(createdDevice)
    }

    fun getDeviceBySerialNumber(serialNumber: String): Device? {
        val eDevice = getDeviceEntityBySerialNumber(serialNumber)

        return mapper.toModel(eDevice)
    }

    private fun getDeviceEntityBySerialNumber(serialNumber: String): EDevice? {
        return deviceRepository.findBySerialNumber(serialNumber)
    }

    fun getOneDevice(deviceId: UUID): Device {
        val eDevice = getOneDeviceEntity(deviceId)

        return mapper.toModel(eDevice)
    }

    private fun getOneDeviceEntity(deviceId: UUID): EDevice {
        return deviceRepository.findByIdOrNull(deviceId) ?: throw DeviceNotFound()
    }
}
