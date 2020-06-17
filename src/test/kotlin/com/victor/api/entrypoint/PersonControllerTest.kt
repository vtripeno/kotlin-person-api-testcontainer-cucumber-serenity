package com.victor.api.entrypoint

import com.victor.api.Application
import com.victor.api.dataprovider.implementation.PersonServiceImpl
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
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import java.io.File


@RunWith(SerenityRunner::class)
@WithTag("Integration")
@AutoConfigureMockMvc
@ContextConfiguration(classes = [Application::class])
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = [HibernateJpaAutoConfiguration::class])
class PersonControllerTest {

    companion object {
        val dockerFilePath : File = File("docker/mongodb-test.dockerfile")
        var mongodbContainer: GenericContainer<*> = GenericContainer<Nothing>(
                ImageFromDockerfile()
                        .withDockerfile(dockerFilePath.toPath())
        ).withExposedPorts(27017)
    }

    init {
        mongodbContainer.start()
        System.setProperty("spring.data.mongodb.uri",
                "mongodb://${mongodbContainer.getContainerIpAddress()}:${mongodbContainer.getMappedPort(27017)}/mycollection")
    }

    @get:Rule
    var springMethodIntegration = SpringIntegrationMethodRule()

    private lateinit var mockMvc: MockMvc
    private lateinit var personController : PersonController
    private lateinit var personServiceImpl: PersonServiceImpl
    private lateinit var personUseCase : PersonUseCase

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        personServiceImpl = PersonServiceImpl(personRepository)
        personUseCase = PersonUseCase(personServiceImpl)
        personController = PersonController(personUseCase)
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build()
    }

    @Test
    fun `test should return  success for findAll persons`() {
        val result: ResultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/person/all"))

        Assert.assertNotNull(result)
        result.andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].personId", Matchers.`is`("999")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name", Matchers.`is`("Zé Mané")))
    }
}