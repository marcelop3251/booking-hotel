package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.BookingEntity
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepositorySpring : CoroutineCrudRepository<BookingEntity, Int> {

    @Query("select count(*) from booking where  (((start_date <= :startDate and end_date >= :startDate) or (start_date <= :endDate and end_date >= :endDate)) \n" +
            "or ((start_date >= :startDate and start_date <= :endDate) or (end_date  >= :startDate and end_date <= :endDate)))" +
            "and room_id = :id and status in (:status)")
    suspend fun countByRoomIdAndStatusInAndDate(id: Int, status: List<String>, startDate: LocalDate, endDate: LocalDate): Int
    suspend fun findAllByCustomerId(customerId: Int): Flow<BookingEntity>
    suspend fun findByCustomerIdAndStatus(customerId: Int, status: String): Flow<BookingEntity>
    @Query(value = "select b.* from booking b inner join room r on b.room_id = r.id " +
            "inner join hotel h on h.id = r.hotel_id " +
            "where h.id in ( " +
            "select h.id from credential c inner join company cp on c.id = cp.credential_id " +
            "inner join hotel h on h.company_id = cp.id " +
            "inner join room r on r.hotel_id = h.id where c.id = $1 group by h.id " +
            ") and b.status = $2"
    )
    suspend fun findAllByStatusAndUserBackoffice(customerId: Int, status: String): Flow<BookingEntity>

    @Modifying
    @Query("UPDATE booking SET status = :status where id = :id")
    suspend fun updateStatusById(status: String, id: Int)
}