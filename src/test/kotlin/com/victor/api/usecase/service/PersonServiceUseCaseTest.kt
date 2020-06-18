package com.victor.api.usecase.service

import com.victor.api.usecase.gateway.PersonService
import com.victor.api.usecase.model.response.PersonEntity
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule
import net.thucydides.core.annotations.WithTag
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(SerenityRunner::class)
@WithTag("Unity")
class PersonServiceUseCaseTest {

    private lateinit var personUseCase: PersonUseCase

    @Mock
    private lateinit var personServiceImplementation: PersonService

    @get:Rule
    var springMethodIntegration = SpringIntegrationMethodRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        personUseCase = PersonUseCase(personServiceImplementation)
    }

    @Test
    fun `test should return success for the use case class`() {
        `when`(personServiceImplementation.findAll()).thenReturn(listOf(PersonEntity(id = "999", firstName = "Victor", lastName = "Tripeno")))

        var personEntity = personUseCase.findAll()

        assertEquals("999", personEntity.get(0).id)
        assertEquals("Victor", personEntity.get(0).firstName)
        assertEquals("Tripeno", personEntity.get(0).lastName)
    }


}