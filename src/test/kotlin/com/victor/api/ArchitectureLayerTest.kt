package com.victor.api

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.runner.RunWith

@RunWith(ArchUnitRunner::class)
@Suppress("VariableNaming")
@AnalyzeClasses(packages = ["com.victor.api"])
class ArchitectureLayerTest {

    @ArchTest
    val `entrypoint must not access dataprovider` =
            noClasses().that().resideInAPackage("..entrypoint..").should()
                    .accessClassesThat().resideInAPackage("..dataprovider..")

    @ArchTest
    val `dataprovider must not access entrypoint` =
            noClasses().that().resideInAPackage("..dataprovider..").should()
                    .accessClassesThat().resideInAPackage("..entrypoint..")

}