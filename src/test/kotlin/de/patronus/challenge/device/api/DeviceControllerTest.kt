package de.patronus.challenge.device.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import de.patronus.challenge.device.business.Device
import de.patronus.challenge.device.business.DeviceService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus.CREATED

@ExtendWith(MockKExtension::class)
class DeviceControllerTest {
    @RelaxedMockK lateinit var deviceService: DeviceService
    @SpyK var dtoMapper = DeviceDTOMapperImpl()
    @InjectMockKs lateinit var sut: DeviceController

    @Test
    fun `should create device and return dto and created status`() {
        val expectedDevice = createDevice()
        val expectedDto = expectedDevice.let(dtoMapper::toDTO)

        every { deviceService.createDevice(any()) } returns expectedDevice

        val response = sut.createDevice(createDeviceDto())

        assertThat(response.statusCode).isEqualTo(CREATED)
        assertThat(response.body).isEqualTo(expectedDto)
    }

    private fun createDevice() = Device(null, "serial number", "model", "123455667789")
    private fun createDeviceDto() = DeviceDTO(null, "serial number", "model", "123455667789")
}