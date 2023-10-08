package de.patronus.challenge.user.api

import de.patronus.challenge.user.business.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface UserDTOMapper {
    fun toDto(model: User): UserDTO
    @Mapping(target = "id", ignore = true)
    fun toModel(dto: UserDTO): User
}