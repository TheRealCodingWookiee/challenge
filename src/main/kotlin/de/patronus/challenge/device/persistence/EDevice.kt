package de.patronus.challenge.device.persistence

import de.patronus.challenge.user.persistence.EUser
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "device")
data class EDevice(
    @Id @GeneratedValue var id: UUID?,
    var serialNumber: String,
    var model: String?,
    var phoneNumber: String?,
    @CreationTimestamp @Column(updatable = false) val creationDate: ZonedDateTime?,
    @UpdateTimestamp val modificationDate: ZonedDateTime?
) {
    @ManyToOne @JoinColumn(name = "users_id") var user: EUser? = null
}
