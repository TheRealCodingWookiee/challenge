package de.patronus.challenge.user.business

import de.patronus.challenge.device.business.Device
import de.patronus.challenge.device.business.DeviceService
import de.patronus.challenge.user.persistence.UserPersistenceService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userPersistenceService: UserPersistenceService,
    private val deviceService: DeviceService
) {
    fun createUser(user: User): User {
        return userPersistenceService.createUser(user)
    }

    fun assignDevice(device: Device, userId: UUID): User {
        deviceService.validateDeviceAssignment(device.id!!)
        return userPersistenceService.assignDeviceToUser(device, userId)
    }

    fun getAllUsersWithDevices(): List<User> {
        return userPersistenceService.findAllUsersWithDevices()
    }
}
