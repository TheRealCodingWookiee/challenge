package de.patronus.challenge.device

import de.patronus.challenge.device.api.DeviceDTO
import de.patronus.challenge.device.business.Device
import de.patronus.challenge.device.persistence.EDevice
import java.time.ZonedDateTime
import java.util.*

object DeviceFixture {
    val ID = UUID.randomUUID()
    val SERIAL_NUMBER = "SerialNumber1"
    val MODEL = "KK1"
    val PHONE_NUMBER = "1234567789"
    val USER_ID = UUID.randomUUID()

    fun createDeviceDTO() =
        DeviceDTO(ID, SERIAL_NUMBER, MODEL, PHONE_NUMBER, USER_ID)

    fun createDevice() =
        Device(ID, SERIAL_NUMBER, MODEL, PHONE_NUMBER, USER_ID)

    fun createEDevice() =
        EDevice(ID, SERIAL_NUMBER, MODEL, PHONE_NUMBER, creationDate = ZonedDateTime.now(), modificationDate = ZonedDateTime.now())

}