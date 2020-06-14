package com.victor.api.container

import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile
import java.io.File
//import javax.validation.constraints.NotNull

class MongoDbContainer: GenericContainer<Nothing> {

    val MONGODB_PORT = 27017

    constructor() :super(ImageFromDockerfile()) {
        val dockerFilePath : File = File("docker/mongodb-test.dockerfile")
        (ImageFromDockerfile()).withDockerfile(dockerFilePath.toPath())
        addExposedPort(MONGODB_PORT)
    }

    fun mappedPort(hostPort: Int, containerPort: Int) {
        this.addFixedExposedPort(hostPort, containerPort);
    }

//    @NotNull
    fun getPort(): Int? {
        return getMappedPort(MONGODB_PORT)
    }

}