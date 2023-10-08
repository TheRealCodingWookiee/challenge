package de.patronus.challenge.device.business

import de.patronus.challenge.device.persistence.DevicePersistenceService
import de.patronus.challenge.user.persistence.UserPersistenceServiceTest
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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

    private fun createDevice() = Device(null, "serial number", "model", "123455667789")
}