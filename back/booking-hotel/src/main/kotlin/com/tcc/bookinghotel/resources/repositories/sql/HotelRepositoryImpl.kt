package com.tcc.bookinghotel.resources.repositories.sql

import com.tcc.bookinghotel.domain.entity.Hotel
import com.tcc.bookinghotel.domain.exception.InconsistentDataException
import com.tcc.bookinghotel.domain.exception.TypeException
import com.tcc.bookinghotel.domain.repository.HotelRepository
import com.tcc.bookinghotel.resources.repositories.entities.HotelEntity
import com.tcc.bookinghotel.resources.repositories.entities.OrganizationEntity
import com.tcc.bookinghotel.resources.repositories.entities.RoomEntity
import com.tcc.bookinghotel.resources.repositories.sql.spring.CompanyRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.HotelRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.OrganizationRepositorySpring
import com.tcc.bookinghotel.resources.repositories.sql.spring.RoomRepositorySpring
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private const val MESSAGE_HOTEL_INCONSISTENCY_DATA = "Not was possible to get organization in hotel"

@Component
class HotelRepositoryImpl(
    private val hotelRepositorySpring: HotelRepositorySpring,
    private val organizationRepositorySpring: OrganizationRepositorySpring,
    private val roomRepositorySpring: RoomRepositorySpring,
    private val companyRepositorySpring: CompanyRepositorySpring
) : HotelRepository {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    override suspend fun findByCNPJ(cnpj: String): Hotel? =
        organizationRepositorySpring.findByCnpj(cnpj)?.let {
            hotelRepositorySpring.findByOrganizationId(it.id!!)?.toDomain(it)
                ?: throw InconsistentDataException(TypeException.INCONSISTENCE, MESSAGE_HOTEL_INCONSISTENCY_DATA)
        }?.also {
            log.info("Hotel found by cnpj com success {}", it)
        }


    @Transactional
    override suspend fun create(hotel: Hotel, userBackofficeId: Int): Hotel =
        companyRepositorySpring.findByCredentialId(userBackofficeId).let { company ->
            organizationRepositorySpring.save(OrganizationEntity(hotel)).let {
                hotelRepositorySpring.save(HotelEntity(hotel, it.id!!, company!!.id!!)).toDomain(it)
            }.also {
                log.info("Hotel create with success {}", it)
            }
        }


    override suspend fun findById(hotelId: Int): Hotel? {
        return hotelRepositorySpring.findById(hotelId)?.let {
            val organization = organizationRepositorySpring.findById(it.organizationId)
                ?: throw InconsistentDataException(TypeException.INCONSISTENCE, MESSAGE_HOTEL_INCONSISTENCY_DATA)
            it.toDomain(organization)
        }
    }

    override suspend fun findAllByUserBackoffice(userBackofficeId: Int): Flow<Hotel> {
        val company = companyRepositorySpring.findByCredentialId(userBackofficeId)
        return hotelRepositorySpring.findAllByCompanyId(company!!.id!!).map {
            val roomEntity = roomRepositorySpring.findByHotelId(it.id!!).toList()
            val organization = organizationRepositorySpring.findById(it.organizationId)
            it.toDomain(organization!!, roomEntity)
        }
    }

    override suspend fun findAll(): Flow<Hotel> {
        return hotelRepositorySpring.findAll().map {
            val organizationEntity = organizationRepositorySpring.findById(it.organizationId)
            val roomEntity = roomRepositorySpring.findByHotelId(it.id!!).toList()
            it.toDomain(organizationEntity!!, roomEntity)
        }
    }

    override suspend fun findByRoomId(roomId: Int): Hotel? {
        return roomRepositorySpring.findById(roomId)?.let { roomEntity ->
            hotelRepositorySpring.findById(roomEntity.hotelId)?.let { hotelEntity ->
                val organizationEntity = organizationRepositorySpring.findById(hotelEntity.organizationId)
                hotelEntity.toDomain(organizationEntity!!, listOf(roomEntity))
            }
        }
    }
}
