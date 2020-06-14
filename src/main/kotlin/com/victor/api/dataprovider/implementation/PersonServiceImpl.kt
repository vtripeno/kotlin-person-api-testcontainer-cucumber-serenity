package com.victor.api.dataprovider.implementation

import com.victor.api.dataprovider.mapper.PersonDaoMapper
import com.victor.api.dataprovider.repository.PersonRepository
import com.victor.api.usecase.gateway.PersonService
import com.victor.api.usecase.model.response.PersonEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service("personImplementation")
class PersonServiceImpl@Autowired constructor(private var personRepository: PersonRepository)
    : PersonService {

    override fun findAll(): List<PersonEntity> {

        val listPeopleDao = personRepository.findAll()

        return PersonDaoMapper.transform(listPeopleDao)
    }

}