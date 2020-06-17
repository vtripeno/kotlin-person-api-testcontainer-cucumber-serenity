package com.victor.api.entrypoint.mapper

import com.victor.api.entrypoint.model.response.PersonDataModelResponse
import com.victor.api.usecase.model.response.PersonEntity

internal class PersonDataModelResponseMapper {
    companion object {
        fun transform(peopleEntity : List<PersonEntity>): List<PersonDataModelResponse> {

            var people = mutableListOf<PersonDataModelResponse>()

            for (person: PersonEntity in peopleEntity) {
                val fullName = StringBuilder(person.firstName)
                        .append(" ")
                        .append(person.lastName).toString()
                people.add(PersonDataModelResponse(person.id, fullName))
            }

            return people

        }
    }
}