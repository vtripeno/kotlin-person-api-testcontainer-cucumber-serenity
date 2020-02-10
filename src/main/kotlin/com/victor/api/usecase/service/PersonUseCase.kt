package com.victor.api.usecase.service

import com.victor.api.dataprovider.implementation.PersonImplementation
import com.victor.api.usecase.gateway.Person
import com.victor.api.usecase.model.response.PersonEntity
import org.springframework.stereotype.Component

@Component
class PersonUseCase {

    fun findAll(): List<PersonEntity> {
        val personImpl : Person = PersonImplementation()
        return personImpl.findAll()
    }
}