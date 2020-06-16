package com.victor.api.dataprovider

import com.victor.api.dataprovider.implementation.PersonServiceImpl
import com.victor.api.dataprovider.repository.PersonRepository
import net.serenitybdd.junit.runners.SerenityRunner
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule
import net.thucydides.core.annotations.WithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.FixedHostPortGenericContainer
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File

@RunWith(SerenityRunner::class)
@WithTag("Unity")
@EnableAutoConfiguration(exclude = [HibernateJpaAutoConfiguration::class])
@EnableMongoRepositories
@TestPropertySource(locations = ["classpath:application-test.yml"])
@Testcontainers
@SpringBootTest
class DockerfileTestContainers {

    private var log = LoggerFactory.getLogger(DockerfileTestContainers::class.java)

    val dockerFilePath : File = File("./docker/mongodb-test.dockerfile")

    @Container

    private val container: FixedHostPortGenericContainer<Nothing>
                = FixedHostPortGenericContainer<Nothing>(
            "mongo")
            .withFixedExposedPort(27017, 27017);


    @get:Rule
    var springMethodIntegration = SpringIntegrationMethodRule()

    @Autowired
    lateinit var personServiceImpl: PersonServiceImpl

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        container.withFileSystemBind("./docker/data.js", "/usr/src/data/docker-entrypoint-initdb.d/", BindMode.READ_WRITE)
        container.waitingFor(Wait.forListeningPort())
        container.start()
        container.followOutput(Slf4jLogConsumer(log))
        personServiceImpl = PersonServiceImpl(personRepository)
    }

    @Test
    fun `teste`() {
        personServiceImpl.findAll()
    }

}