package de.patronus.challenge.user.persistence

import de.patronus.challenge.device.business.Device
import de.patronus.challenge.device.persistence.DeviceEntityMapper
import de.patronus.challenge.user.business.User
import de.patronus.challenge.user.business.UserNotFound
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserPersistenceService(
    private val userRepository: UserRepository,
    private val userEntityMapper: UserEntityMapper,
    private val deviceEntityMapper: DeviceEntityMapper
) {
    fun createUser(user: User): User {
        val eUser = userRepository.save(userEntityMapper.toEntity(user))

        return userEntityMapper.toModel(eUser)
    }

    fun findAllUsersWithDevices(): List<User> {
        val eUsers = userRepository.findAllByDevicesIsNotNull()
        if (eUsers.isEmpty()) {
            throw UserNotFound()
        }

        return userEntityMapper.toModelList(eUsers)
    }

    fun assignDeviceToUser(device: Device, userId: UUID): User {
        val eUser = getOneUserEntity(userId)
        val eDevice = deviceEntityMapper.toEntity(device)

        eUser.addDevice(eDevice)

        return userEntityMapper.toModel(userRepository.save(eUser))
    }

    private fun getOneUserEntity(userId: UUID): EUser {
        return userRepository.findByIdOrNull(userId) ?: throw UserNotFound()
    }
}