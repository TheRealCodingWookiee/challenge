package de.patronus.challenge.device.persistence

import assertk.assertThat
import assertk.assertions.containsExactly
import de.patronus.challenge.device.DeviceFixture
import de.patronus.challenge.device.DeviceFixture.SERIAL_NUMBER
import de.patronus.challenge.device.DeviceFixture.createDevice
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

}