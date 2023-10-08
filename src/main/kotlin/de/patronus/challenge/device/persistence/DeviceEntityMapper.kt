package de.patronus.challenge.device.persistence

import de.patronus.challenge.device.business.Device
import org.mapstruct.Mapper
import org.mapstruct.Mapping


@Mapper
interface DeviceEntityMapper {
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "modificationDate", ignore = true)
    fun toEntity(model: Device): EDevice
    fun toModel(entity: EDevice): Device
}
