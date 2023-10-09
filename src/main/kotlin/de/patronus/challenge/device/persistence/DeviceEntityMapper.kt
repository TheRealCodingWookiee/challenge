package de.patronus.challenge.device.persistence

import de.patronus.challenge.device.business.Device
import org.mapstruct.Mapper
import org.mapstruct.Mapping


@Mapper
interface DeviceEntityMapper {
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "modificationDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    fun toEntity(model: Device): EDevice
    @Mapping(target = "userId", source = "user.id")
    fun toModel(entity: EDevice?): Device
}
