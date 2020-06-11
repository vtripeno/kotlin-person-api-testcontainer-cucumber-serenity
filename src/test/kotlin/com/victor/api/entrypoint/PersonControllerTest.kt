package com.victor.api.entrypoint

import com.victor.api.dataprovider.model.response.Person
import com.victor.api.dataprovider.repository.PersonRepository
import com.victor.api.entrypoint.controller.PersonController
import com.victor.api.usecase.service.PersonUseCase
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule
import net.thucydides.core.annotations.WithTag
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SerenityRunner::class)
@WithTag("Integration")
@AutoConfigureMockMvc
@WebMvcTest
class PersonControllerTest {

    private lateinit var personController : PersonController

    @Mock
    private lateinit var personUseCase : PersonUseCase

    @MockBean
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Rule @JvmField
    var springMethodIntegration = SpringIntegrationMethodRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        personController = PersonController(personUseCase)
    }

    @Test
    fun `test should return  success for findAll persons`() {
        `when`(personRepository.findAll()).thenReturn(listOf(Person(id = "999", firstname = "Zé", lastname = "Mané")))

        val result: ResultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/person/all"))

        Assert.assertNotNull(result)
        result.andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].personId", Matchers.`is`("999")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name", Matchers.`is`("Zé Mané")))
    }
}