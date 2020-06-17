package com.victor.api.usecase.gateway

import com.victor.api.usecase.model.response.PersonEntity

interface PersonService {
    fun findAll(): List<PersonEntity>
}