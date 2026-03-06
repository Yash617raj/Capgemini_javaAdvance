package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.LoginRequest;
import com.cap.BookStroreRest.DataTransferObject.PageResponse;
import com.cap.BookStroreRest.DataTransferObject.UserDto;
import com.cap.BookStroreRest.Entity.User;
import com.cap.BookStroreRest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.cap.BookStroreRest.DataTransferObject.PageResponse;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto registerUser(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public String loginUser(LoginRequest loginDto) {

        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return "Login Successful";
    }


    public UserDto updateUser(Long id, UserDto userDto) {

        User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }
    public UserDto deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    public PageResponse<UserDto> getUsers(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> userPage = userRepository.findAll(pageable);

        List<UserDto> userDtoList = userPage.getContent()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();

        return new PageResponse<>(
                userDtoList,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages()
        );
    }
}