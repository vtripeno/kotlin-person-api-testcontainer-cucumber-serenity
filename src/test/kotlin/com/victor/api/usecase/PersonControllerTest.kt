package com.victor.api.usecase

import com.victor.api.entrypoint.controller.PersonController
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule
import net.thucydides.core.annotations.WithTag
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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

    @Autowired
    private lateinit var mvc: MockMvc

    @Rule @JvmField
    var springMethodIntegration = SpringIntegrationMethodRule()

    @InjectMocks
    lateinit var personController : PersonController

//    @Mock
//    @Spy
//    private val personDaoResponse :  PersonDaoResponse = PersonDaoResponse()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `integration success`() {
//        `when`(personDaoResponse.findAll()).thenReturn(listOf(PersonDaoResponse(999, "Zé")))


        val result: ResultActions = this.mvc.perform(
                MockMvcRequestBuilders.get("/person/all"))

        Assert.assertNotNull(result);
        result.andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].personId", Matchers.`is`(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name", Matchers.`is`("José")))

    }
}