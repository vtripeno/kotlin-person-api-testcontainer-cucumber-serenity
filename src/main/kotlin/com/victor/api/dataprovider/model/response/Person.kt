package com.victor.api.dataprovider.model.response

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class  Person @PersistenceConstructor constructor(@Id val id: String?, val firstName: String? = "", val lastName: String = "") {
    constructor(firstname: String?, lastname: String) : this(null, firstname, lastname);
}