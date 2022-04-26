package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Hotel
import com.tcc.bookinghotel.domain.exception.InconsistentDataException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.resources.repositories.entities.HotelEntity
import com.tcc.bookinghotel.resources.repositories.entities.OrganizationEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.HotelRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.OrganizationRepositorySpring
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private const val MESSAGE_HOTEL_INCONSISTENCY_DATA = "Not was possible to get organization in hotel"

@Component
class HotelRepositoryImpl(
    private val hotelRepositorySpring: HotelRepositorySpring,
    private val organizationRepositorySpring: OrganizationRepositorySpring,
) : HotelRepository {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    override suspend fun findByCNPJ(cnpj: String): Hotel? =
        organizationRepositorySpring.findByCnpj(cnpj)?.let {
            hotelRepositorySpring.findByOrganizationId(it.id!!)?.toDomain(it)
                ?: throw InconsistentDataException(TypeException.HOTEL_INCONSISTENCE, MESSAGE_HOTEL_INCONSISTENCY_DATA)
        }?.also {
            log.info("Hotel found by cnpj com success {}",it)
        }


    @Transactional
    override suspend fun create(hotel: Hotel, companyId: Int): Hotel  =
        organizationRepositorySpring.save(OrganizationEntity(hotel)).let {
            hotelRepositorySpring.save(HotelEntity(hotel, it.id!!, companyId)).toDomain(it)
        }.also {
            log.info("Hotel create with success {}", it)
        }

    override suspend fun findById(hotelId: Int): Hotel {
        TODO("Not yet implemented")
    }
}