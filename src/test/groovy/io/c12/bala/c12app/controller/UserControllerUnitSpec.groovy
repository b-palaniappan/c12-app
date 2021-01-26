package io.c12.bala.c12app.controller

import io.c12.bala.c12app.dto.UserDto
import io.c12.bala.c12app.service.UserService
import io.c12.bala.c12app.utils.JsonTestUtils
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class UserControllerUnitSpec extends Specification {

    UserService userService = Mock()
    UserController userController = new UserController(userService)
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build()

    def "verify service method id called for create user api request"() {
        given: "Create test user to be created"
        UserDto userDto = new UserDto(null, "Adam", "Sandler", "adam@testmail.com", "adam_16", null)
        UserDto savedUserDto = new UserDto("7N9SxpXKw2MOObQVzhPrG", "Adam", "Sandler", "adam@testmail.com", "adam_16", null)

        when: "create user api is called with new user payload"
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonTestUtils.asJsonString(userDto)))

        then: "user service method is called once"
        1 * userService.addUser(userDto) >> savedUserDto
    }

    def "verify response code is 201 for create user api request"() {
        given: "Create test user to be created"
        UserDto userDto = new UserDto(null, "Adam", "Sandler", "adam@testmail.com", "adam_16", null)
        UserDto savedUserDto = new UserDto("7N9SxpXKw2MOObQVzhPrG", "Adam", "Sandler", "adam@testmail.com", "adam_16", null)

        when: "create user api is called with new user payload"
        def response = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonTestUtils.asJsonString(userDto)))

        then: "user service method is called once"
        1 * userService.addUser(userDto) >> savedUserDto

        and: "response code is 200"
        response.andExpect(status().isCreated())
                .andDo(print())
                .andReturn()

    }

    def "verify response code and response body for create user api request"() {
        given: "Create test user to be created"
        UserDto userDto = new UserDto(null, "Adam", "Sandler", "adam@testmail.com", "adam_16", null)
        UserDto savedUserDto = new UserDto("7N9SxpXKw2MOObQVzhPrG", "Adam", "Sandler", "adam@testmail.com", "adam_16", null)

        when: "create user api is called with new user payload"
        def response = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonTestUtils.asJsonString(userDto)))

        then: "user service method is called once"
        1 * userService.addUser(userDto) >> savedUserDto

        and: "response body have id with correct value"
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("\$.id").exists())
                .andExpect(jsonPath("\$.id").value("7N9SxpXKw2MOObQVzhPrG"))
                .andDo(print())
                .andReturn()

    }

    def "verify response code and response body and http header location for create user api request"() {
        given: "Create test user to be created"
        UserDto userDto = new UserDto(null, "Adam", "Sandler", "adam@testmail.com", "adam_16", null)
        UserDto savedUserDto = new UserDto("7N9SxpXKw2MOObQVzhPrG", "Adam", "Sandler", "adam@testmail.com", "adam_16", null)

        when: "create user api is called with new user payload"
        def response = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonTestUtils.asJsonString(userDto)))

        then: "user service method is called once"
        1 * userService.addUser(userDto) >> savedUserDto

        and: "response body have id with correct value"
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("\$.id").exists())
                .andExpect(jsonPath("\$.id").value("7N9SxpXKw2MOObQVzhPrG"))
                .andExpect(header().string("Location", "http://localhost/api/v1/users/7N9SxpXKw2MOObQVzhPrG"))
                .andDo(print())
                .andReturn()

    }
}
