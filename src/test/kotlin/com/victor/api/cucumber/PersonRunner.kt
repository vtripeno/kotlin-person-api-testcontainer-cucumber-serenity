package com.victor.api.cucumber

import cucumber.api.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        strict = true,
        plugin = ["pretty", "html:target/cucumber-html-report", "junit:target/junit-report.xml"],
        monochrome = false,
        glue = ["com.victor.api.cucumber"],
        features = ["classpath:features"]
)
class PersonRunner {}