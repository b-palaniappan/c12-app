package io.c12.bala.c12app.controller;

import io.c12.bala.c12app.dto.UserDto;
import io.c12.bala.c12app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private static final String USER_CONTROLLER_USERS_ENDPOINT = "users";
    private final UserService userService;
    private final PagedResourcesAssembler<UserDto> pagedResourcesAssembler;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntityModel<UserDto>> createUser(@Valid @RequestBody UserDto user) {
        log.info("Add user");
        UserDto savedUser = userService.addUser(user);
        EntityModel<UserDto> userModel = EntityModel.of(savedUser);
        userModel.add(linkTo(UserController.class).slash(savedUser.getId()).withSelfRel());
        userModel.add(linkTo(UserController.class).withRel(USER_CONTROLLER_USERS_ENDPOINT));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(linkTo(UserController.class).slash(savedUser.getId()).toUri());
        return new ResponseEntity<>(userModel, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EntityModel<UserDto>> updateUser(@PathVariable String id, @Valid @RequestBody UserDto user) {
        user.setId(id);
        UserDto savedUser = userService.updateUser(user);
        EntityModel<UserDto> userModel = EntityModel.of(savedUser);
        userModel.add(linkTo(UserController.class).slash(savedUser.getId()).withSelfRel());
        userModel.add(linkTo(UserController.class).withRel(USER_CONTROLLER_USERS_ENDPOINT));
        return ResponseEntity.ok(userModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        if (userService.deleteUser(id)) {
            log.info("user {}, found and deleted successfully", id);
        } else {
            log.info("user {}, delete failed", id);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PagedModel<EntityModel<UserDto>>> getAllUsers(Pageable pageable) {
        Page<UserDto> users = userService.getAllUsers(pageable);
        PagedModel<EntityModel<UserDto>> userPagedModel = pagedResourcesAssembler.toModel(users);
        userPagedModel.forEach(user -> user.add(linkTo(UserController.class).slash(Objects.requireNonNull(user.getContent()).getId()).withSelfRel()));
        userPagedModel.add(linkTo(UserController.class).withRel(USER_CONTROLLER_USERS_ENDPOINT));
        userPagedModel.getNextLink();
        userPagedModel.getPreviousLink();
        return ResponseEntity.ok().body(userPagedModel);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

}
