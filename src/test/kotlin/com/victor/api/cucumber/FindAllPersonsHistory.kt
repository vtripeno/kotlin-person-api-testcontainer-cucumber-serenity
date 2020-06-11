package com.victor.api.cucumber

import com.victor.api.dataprovider.model.response.Person
import com.victor.api.dataprovider.repository.PersonRepository
import com.victor.api.entrypoint.controller.PersonController
import com.victor.api.usecase.service.PersonUseCase
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.restassured.module.mockmvc.RestAssuredMockMvc
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ContextConfiguration
@AutoConfigureMockMvc
@WebMvcTest
class FindAllPersonsHistory {

    private lateinit var result: ResultActions

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
        RestAssuredMockMvc.standaloneSetup(PersonController(personUseCase))
        personController = PersonController(personUseCase)
    }

    @Given("a person endpoint to bring all persons")
    fun personEndpoint() {
        Mockito.`when`(personRepository.findAll()).thenReturn(listOf(Person(id = "999", firstname = "Zé", lastname = "Mané")))
        this.result = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/person/all"))
    }

    @When("the return is success")
    fun verifySuccess() {
        Assert.assertNotNull(this.result)
        this.result.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Then("I get a list of all persons")
    fun verifyListOfAllPersons() {
        this.result
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].personId", Matchers.`is`("999")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name", Matchers.`is`("Zé Mané")))
    }
}