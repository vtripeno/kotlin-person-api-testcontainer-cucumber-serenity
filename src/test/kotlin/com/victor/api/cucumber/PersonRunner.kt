package com.victor.api.cucumber

import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        plugin = ["pretty", "html:target/cucumber-html-report", "junit:target/junit-report.xml"],
        glue = ["com.victor.api.cucumber"],
        features = ["classpath:features"]
)
class PersonRunner {}