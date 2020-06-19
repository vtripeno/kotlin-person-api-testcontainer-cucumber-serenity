package com.victor.api.dataprovider.mapper;

import com.victor.api.dataprovider.model.response.Person;
import com.victor.api.usecase.model.response.PersonEntity;

internal class PersonDaoMapper {

    companion object {

        fun transform(people: List<Person>): List<PersonEntity> {
            return people.map ( ::toPersonEntity )
        }

        private fun toPersonEntity(person: Person): PersonEntity {
            return PersonEntity(person.id, person.firstName, person.lastName)
        }

    }
}
