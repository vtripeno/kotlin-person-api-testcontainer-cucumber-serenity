package com.victor.api.entrypoint.controller

import com.victor.api.entrypoint.mapper.PersonDataModelResponseMapper
import com.victor.api.entrypoint.model.response.DataModelResponse
import com.victor.api.entrypoint.model.response.PersonDataModelResponse
import com.victor.api.usecase.service.PersonUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController @Autowired constructor(
        private val personUseCase : PersonUseCase){

    @GetMapping("/all")
    fun findAllPeople(): DataModelResponse<List<PersonDataModelResponse>> =
            DataModelResponse(
                PersonDataModelResponseMapper.transform(personUseCase.findAll()))

}