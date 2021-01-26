package io.c12.bala.c12app.service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import io.c12.bala.c12app.dto.UserDto;
import io.c12.bala.c12app.entity.User;
import io.c12.bala.c12app.exception.UserNotFoundException;
import io.c12.bala.c12app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    public UserDto addUser(UserDto newUser) {
        User user = modelMapper.map(newUser, User.class);
        user.setId(NanoIdUtils.randomNanoId());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        userRepository.findById(user.getId()).orElseThrow(new UserNotFoundException());
        User savedUser = userRepository.save(modelMapper.map(user, User.class));
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public boolean deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(new UserNotFoundException());
        log.info("User to be deleted is {}", user);
        userRepository.delete(user);
        return true;
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return new PageImpl<>(userRepository.findAll(pageable).stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList()));
    }

    @Override
    public UserDto getUser(String userId) {
        return modelMapper.map(userRepository.findById(userId).orElseThrow(new UserNotFoundException()), UserDto.class);
    }
}
