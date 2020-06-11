package com.victor.api.usecase.service

import com.victor.api.usecase.gateway.Person
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
class PersonUseCaseTest {

    private lateinit var personUseCase: PersonUseCase

    @Mock
    private lateinit var personImplementation: Person

    @Rule
    @JvmField
    var springMethodIntegration = SpringIntegrationMethodRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        personUseCase = PersonUseCase(personImplementation)
    }

    @Test
    fun `test should return success for the use case class`() {
        `when`(personImplementation.findAll()).thenReturn(listOf(PersonEntity(id = "999", name = "Zé Mané")))

        var personEntity = personUseCase.findAll()

        assertEquals("999", personEntity.get(0).id)
        assertEquals("Zé Mané", personEntity.get(0).name)
    }


}