package com.tcc.bookinghotel.resources.repositories.sql.spring

import com.tcc.bookinghotel.resources.repositories.entities.RequestServiceEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface RequestServiceRepositorySpring : CoroutineCrudRepository<RequestServiceEntity, Int>