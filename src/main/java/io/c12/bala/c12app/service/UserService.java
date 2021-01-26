package io.c12.bala.c12app.service;

import io.c12.bala.c12app.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto addUser(UserDto newUser);

    UserDto updateUser(UserDto user);

    boolean deleteUser(String userId);

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto getUser(String userId);

}
