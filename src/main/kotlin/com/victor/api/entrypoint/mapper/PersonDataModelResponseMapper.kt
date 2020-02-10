package com.victor.api.entrypoint.mapper

import com.victor.api.entrypoint.model.response.PersonDataModelResponse
import com.victor.api.usecase.model.response.PersonEntity

internal class PersonDataModelResponseMapper {
    companion object {
        fun transform(peopleEntity : List<PersonEntity>): List<PersonDataModelResponse> {

            var people = mutableListOf<PersonDataModelResponse>()

            for (person: PersonEntity in peopleEntity) {
                people.add(PersonDataModelResponse(person.id, person.name))
            }

            return people

        }
    }
}