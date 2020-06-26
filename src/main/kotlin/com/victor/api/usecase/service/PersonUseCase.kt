package com.victor.api.usecase.service

import com.victor.api.usecase.gateway.PersonService
import com.victor.api.usecase.model.response.PersonEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PersonUseCase @Autowired constructor(private val personService : PersonService){

    fun findAll(): List<PersonEntity> = personService.findAll()
}