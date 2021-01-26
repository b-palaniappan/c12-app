package io.c12.bala.c12app.controller

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class HealthControllerUnitSpec extends Specification {

    HealthController healthController = new HealthController()
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(healthController).build()

    def "health controller return 200 status code"() {
        expect: "Status is 200"
        mockMvc.perform(get("/api/v1/health")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
    }

    def "health controller return 200 status code and body with status ok"() {
        expect: "status is 200 and body is status ok"
        mockMvc.perform(get("/api/v1/health")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$.status").exists())
                .andExpect(jsonPath("\$.status").value("ok"))
                .andDo(print())
                .andReturn()
    }

    def "ping controller return 200 status code"() {
        expect: "Status is 200"
        mockMvc.perform(get("/api/v1/ping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
    }

    def "ping controller return 200 status code and body with status ok"() {
        expect: "status is 200 and body is status ok"
        mockMvc.perform(get("/api/v1/ping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$.status").exists())
                .andExpect(jsonPath("\$.status").value("ok"))
                .andDo(print())
                .andReturn()
    }

}
