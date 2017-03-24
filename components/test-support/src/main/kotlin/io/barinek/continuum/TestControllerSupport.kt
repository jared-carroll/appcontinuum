package io.barinek.continuum

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.web.client.RestTemplate

open class TestControllerSupport() {
    val mapper = ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule())!!
    val template = RestTemplate()
}