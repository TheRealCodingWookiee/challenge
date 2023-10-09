package de.patronus.challenge.device.persistence

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import de.patronus.challenge.device.DeviceFixture
import de.patronus.challenge.device.DeviceFixture.ID
import de.patronus.challenge.device.DeviceFixture.SERIAL_NUMBER
import de.patronus.challenge.device.DeviceFixture.createDevice
import de.patronus.challenge.device.business.DeviceNotFound
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import

@DataJpaTest(
    includeFilters = [ComponentScan.Filter(type = FilterType.REGEX, pattern = [".*MapperImpl"])]
)
@Import(DevicePersistenceService::class)
class DevicePersistenceServiceTest {
    @Autowired private lateinit var sut: DevicePersistenceService
    @Autowired private lateinit var deviceRepository: DeviceRepository

    @Test
    fun `should save device`() {
        sut.createDevice(createDevice())

        assertThat(deviceRepository.findAll().map { it.serialNumber }).containsExactly(SERIAL_NUMBER)
    }

    @Test
    fun `should get one device if exist`() {
        val createdDevice = sut.createDevice(createDevice())

        val result = sut.getOneDevice(createdDevice.id!!)

        assertThat(result).isEqualTo(createdDevice)
    }

    @Test
    fun `should throw exception when not found`() {
        sut.createDevice(createDevice())

       assertFailure { sut.getOneDevice(ID) }.isInstanceOf(DeviceNotFound::class)
    }
}