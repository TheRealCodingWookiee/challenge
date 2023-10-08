package de.patronus.challenge.user.persistence

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.Date
import java.util.UUID


@Entity
@Table(name = "users") //users because else exception since it is keyword for hibernate
data class EUser(
    @Id @GeneratedValue var id: UUID?,
    var firstName: String,
    var lastName: String,
    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true) var address: EAddress,
    var birthday: LocalDate,
    @CreationTimestamp @Column(updatable = false) val creationDate: ZonedDateTime?,
    @UpdateTimestamp val modificationDate: ZonedDateTime?
)

@Entity
@Table(name = "address")
data class EAddress(
    @Id @GeneratedValue val id: UUID?,
    var street: String,
    var houseNumber: String,
    var city: String,
    var postalCode: String,
    @CreationTimestamp @Column(updatable = false) val creationDate: ZonedDateTime?,
    @UpdateTimestamp val modificationDate: ZonedDateTime?
)
