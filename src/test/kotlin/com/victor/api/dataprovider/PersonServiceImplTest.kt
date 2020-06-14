package com.victor.api.dataprovider

import com.victor.api.dataprovider.implementation.PersonServiceImpl
import com.victor.api.dataprovider.repository.PersonRepository
import com.victor.api.entrypoint.controller.PersonController
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule
import net.thucydides.core.annotations.WithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import java.io.File

@RunWith(SerenityRunner::class)
@WithTag("Unity")
@EnableAutoConfiguration(exclude = [HibernateJpaAutoConfiguration::class])
@EnableMongoRepositories
@TestPropertySource
class PersonServiceImplTest {

    val dockerFilePath : File = File("docker/mongodb-test.dockerfile")

    @get:Rule
    var mongodbContainer: GenericContainer<*> = GenericContainer<Nothing>(
                    ImageFromDockerfile()
                            .withDockerfile(dockerFilePath.toPath())
            ).withExposedPorts(27017)

    @get:Rule
    var springMethodIntegration = SpringIntegrationMethodRule()

//    @Autowired
    lateinit var personServiceImpl: PersonServiceImpl

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Before
    fun init() {
        mongodbContainer.setPortBindings(listOf("27017:27017"))
        MockitoAnnotations.initMocks(this)
        personServiceImpl = PersonServiceImpl(personRepository)
    }

    @Test
    fun `teste`() {
        personServiceImpl.findAll()
    }
}