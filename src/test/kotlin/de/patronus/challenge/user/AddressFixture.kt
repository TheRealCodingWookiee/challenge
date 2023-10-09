package de.patronus.challenge.user

import de.patronus.challenge.user.AddressFixture.CITY
import de.patronus.challenge.user.AddressFixture.HOUSE_NUMBER
import de.patronus.challenge.user.AddressFixture.ID
import de.patronus.challenge.user.AddressFixture.POSTAL_CODE
import de.patronus.challenge.user.AddressFixture.STREET
import de.patronus.challenge.user.api.AddressDTO
import de.patronus.challenge.user.business.Address
import de.patronus.challenge.user.persistence.EAddress
import java.time.ZonedDateTime
import java.util.*

object AddressFixture {
    val ID = UUID.randomUUID()
    val STREET = "Street"
    val HOUSE_NUMBER = "1"
    val CITY = "City"
    val POSTAL_CODE = "12345"

    fun createAddressDTO() = AddressDTO(STREET, HOUSE_NUMBER, CITY, POSTAL_CODE)
    fun createAddress() = Address(STREET, HOUSE_NUMBER, CITY, POSTAL_CODE)
    fun createEAddress() = EAddress(ID, STREET, HOUSE_NUMBER, CITY, POSTAL_CODE, creationDate = ZonedDateTime.now(), ZonedDateTime.now())

}

