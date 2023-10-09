package de.patronus.challenge.device.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface DeviceRepository : JpaRepository<EDevice, UUID> {
    fun findBySerialNumber(serialNumber: String): EDevice?
}
