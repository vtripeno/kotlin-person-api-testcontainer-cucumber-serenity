package com.victor.api.dataprovider.repository

import com.victor.api.dataprovider.model.response.Person
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: MongoRepository<Person, String> {
}