package de.patronus.challenge.device.business

import assertk.assertFailure
import assertk.assertions.isInstanceOf
import de.patronus.challenge.device.DeviceFixture.createDevice
import de.patronus.challenge.device.persistence.DevicePersistenceService
import de.patronus.challenge.user.persistence.UserPersistenceServiceTest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class DeviceServiceTest {
    @RelaxedMockK lateinit var devicePersistenceService: DevicePersistenceService
    @InjectMockKs lateinit var sut: DeviceService


    @Test
    fun `should call persistence service for creating device`() {
        val device = createDevice()

        sut.createDevice(device)

        verify { devicePersistenceService.createDevice(device) }
    }

    @Test
    fun `should be valid if device is not assigned to user`() {
        val device = createDevice().copy(userId = null)
        every { devicePersistenceService.getOneDevice(any()) } returns device

        val result = sut.validateDeviceAssignment(device.id!!)

        assertThat(result).isTrue()
    }

    @Test
    fun `should throw error when device already assigned to user`() {
        val device = createDevice()
        every { devicePersistenceService.getOneDevice(any()) } returns device

        assertFailure { sut.validateDeviceAssignment(device.id!!) }.isInstanceOf(DeviceAlreadyAssigned::class)
    }

}