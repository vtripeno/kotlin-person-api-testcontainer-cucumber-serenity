package com.victor.api.usecase.gateway

import com.victor.api.usecase.model.response.PersonEntity

interface Person {
    fun findAll(): List<PersonEntity>
}