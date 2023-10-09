package de.patronus.challenge.user.api

import de.patronus.challenge.device.api.DeviceDTOMapper
import de.patronus.challenge.user.business.User
import org.mapstruct.Mapper

@Mapper(uses = [DeviceDTOMapper::class])
interface UserDTOMapper {
    fun toDto(model: User): UserDTO
    fun toModel(dto: UserDTO): User
    fun toDtoList(users: List<User>): List<UserDTO>
    fun toUserListDTO(users: List<User>) = UserListDTO(toDtoList(users))
}