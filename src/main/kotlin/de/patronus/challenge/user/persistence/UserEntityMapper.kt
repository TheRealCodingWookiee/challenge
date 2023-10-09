package de.patronus.challenge.user.persistence

import de.patronus.challenge.device.persistence.DeviceEntityMapper
import de.patronus.challenge.user.business.Address
import de.patronus.challenge.user.business.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(uses = [DeviceEntityMapper::class])
interface UserEntityMapper {
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "modificationDate", ignore = true)
    fun toEntity(model: User): EUser
    fun toModel(entity: EUser): User

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "modificationDate", ignore = true)
    fun toEntity(model: Address): EAddress
    fun toModel(entity: EAddress): Address
}
