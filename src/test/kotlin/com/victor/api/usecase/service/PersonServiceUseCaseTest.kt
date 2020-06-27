package com.victor.api.usecase.service

import com.victor.api.usecase.gateway.PersonService
import com.victor.api.usecase.model.response.PersonEntity
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
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

    @MockK
    private lateinit var personServiceImplementationMockK: PersonService

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

        val personEntity = personUseCase.findAll()

        assertEquals("999", personEntity[0].id)
        assertEquals("Victor", personEntity[0].firstName)
        assertEquals("Tripeno", personEntity[0].lastName)
    }

    @Test
    fun `test should return success for the use case class mockK`() {
        personUseCase = PersonUseCase(personServiceImplementationMockK)

        every { personServiceImplementationMockK.findAll() } returns listOf(PersonEntity(id = "999", firstName = "Victor", lastName = "Tripeno"))

        val personEntity = personUseCase.findAll()

        assertEquals("999", personEntity[0].id)
        assertEquals("Victor", personEntity[0].firstName)
        assertEquals("Tripeno", personEntity[0].lastName)

        verify(exactly = 1) { personServiceImplementationMockK.findAll() }
    }

}
