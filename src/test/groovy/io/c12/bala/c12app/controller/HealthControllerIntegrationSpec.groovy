package io.c12.bala.c12app.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.MongoDBContainer
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
// DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD is to start springboot for each test case
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class HealthControllerIntegrationSpec extends Specification {

    @Shared
    MongoDBContainer mongoDBContainer

    def setupSpec() {       // runs once -  before the first feature method
        mongoDBContainer = new MongoDBContainer()
        mongoDBContainer.start()
    }

    def cleanupSpec() {     // runs once -  after the last feature method
        mongoDBContainer.stop()
    }

    @Autowired
    private MockMvc mockMvc

    def "Health controller return status code 200"() {
        expect: "health check url will return status code 200"
        mockMvc.perform(get("/api/v1/health")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
    }

    def "health controller return 200 status code and body with status ok"() {
        expect: "status is 200 and body is status ok"
        mockMvc.perform(get("/api/v1/health")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$.status").exists())
                .andExpect(jsonPath("\$.status").value("ok"))
                .andDo(print())
                .andReturn()
    }

    def "ping controller return 200 status code"() {
        expect: "Status is 200"
        mockMvc.perform(get("/api/v1/ping")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
    }

    def "ping controller return 200 status code and body with status ok"() {
        expect: "status is 200 and body is status ok"
        mockMvc.perform(get("/api/v1/ping")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$.status").exists())
                .andExpect(jsonPath("\$.status").value("ok"))
                .andDo(print())
                .andReturn()
    }
}
