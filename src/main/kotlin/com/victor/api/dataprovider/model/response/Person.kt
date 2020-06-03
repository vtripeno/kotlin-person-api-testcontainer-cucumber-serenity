package com.victor.api.dataprovider.model.response

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor

data class  Person @PersistenceConstructor constructor(@Id val id: String?, val firstname: String? = "", val lastname: String = "") {
    constructor(firstname: String?, lastname: String) : this(null, firstname, lastname);
}