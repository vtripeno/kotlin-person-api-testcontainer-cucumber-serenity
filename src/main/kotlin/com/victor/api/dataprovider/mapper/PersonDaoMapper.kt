package com.victor.api.dataprovider.mapper;

import com.victor.api.dataprovider.model.response.Person;
import com.victor.api.usecase.model.response.PersonEntity;

internal class PersonDaoMapper {

    companion object {

        fun transform(peopleDaoReponse: List<Person>): List<PersonEntity> {
            var people = mutableListOf<PersonEntity>()

            for (person: Person in peopleDaoReponse) {
                val fullName = StringBuilder(person.firstname)
                                        .append(" ")
                                        .append(person.lastname).toString()
                people.add(PersonEntity(person.id, fullName))
            }

            return people
        }
    }
}
