package com.victor.api.dataprovider.model.response

internal class PersonDaoResponse (
        val identification: Long = 0,
        val personName: String = "") {

    fun findAll(): List<PersonDaoResponse> {
        return listOf(PersonDaoResponse(1, "José"))
    }
}