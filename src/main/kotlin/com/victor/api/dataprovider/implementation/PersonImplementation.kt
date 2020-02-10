package com.victor.api.dataprovider.implementation

import com.victor.api.dataprovider.mapper.PersonDaoMapper
import com.victor.api.dataprovider.model.response.PersonDaoResponse
import com.victor.api.usecase.gateway.Person
import com.victor.api.usecase.model.response.PersonEntity

class PersonImplementation : Person {

    override fun findAll(): List<PersonEntity> {

        val listPeopleDao : List<PersonDaoResponse> = listOf(PersonDaoResponse(1, "José"))

        return PersonDaoMapper.transform(listPeopleDao)
    }

}