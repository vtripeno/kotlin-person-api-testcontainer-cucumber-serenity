package com.victor.api.dataprovider.mapper;

import com.victor.api.dataprovider.model.response.PersonDaoResponse;
import com.victor.api.usecase.model.response.PersonEntity;

internal class PersonDaoMapper {

    companion object {

        fun transform(peopleDaoReponse: List<PersonDaoResponse>): List<PersonEntity> {
            var people = mutableListOf<PersonEntity>()

            for (person: PersonDaoResponse in peopleDaoReponse) {
                people.add(PersonEntity(person.identification, person.personName))
            }

            return people
        }
    }
}
