package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.ServiceEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ServiceRepositorySpring : CoroutineCrudRepository<ServiceEntity, Int>