package de.patronus.challenge.device.api

import de.patronus.challenge.device.business.Device
import org.mapstruct.Mapper


@Mapper
interface DeviceDTOMapper {
    fun toDTO(model: Device): DeviceDTO
    fun toModel(dto: DeviceDTO): Device
}
