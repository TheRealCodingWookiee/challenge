package de.patronus.challenge.user

import de.patronus.challenge.device.DeviceFixture
import de.patronus.challenge.user.api.UserDTO
import de.patronus.challenge.user.business.User
import de.patronus.challenge.user.persistence.EUser
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

object UserFixture {
    val ID = UUID.randomUUID()
    val FIRST_NAME = "First"
    val LAST_NAME = "Last"
    val BIRTHDAY = LocalDate.of(2000, 1,1)

    fun createUserDTO() = UserDTO(ID, FIRST_NAME, LAST_NAME, AddressFixture.createAddressDTO(), BIRTHDAY, listOf(DeviceFixture.createDeviceDTO()))
    fun createUser() = User(ID, FIRST_NAME, LAST_NAME, AddressFixture.createAddress(), BIRTHDAY, listOf(DeviceFixture.createDevice()))
    fun createEUser() = EUser(ID, FIRST_NAME, LAST_NAME, AddressFixture.createEAddress(), BIRTHDAY, mutableListOf(DeviceFixture.createEDevice()), creationDate = ZonedDateTime.now(), modificationDate = ZonedDateTime.now())
}