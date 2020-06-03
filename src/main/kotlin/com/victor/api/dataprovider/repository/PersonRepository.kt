package com.victor.api.dataprovider.repository

import com.victor.api.dataprovider.model.response.Person
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
internal interface PersonRepository: MongoRepository<Person, String> {
}