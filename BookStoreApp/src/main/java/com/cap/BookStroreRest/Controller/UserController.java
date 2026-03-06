package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.LoginRequest;
import com.cap.BookStroreRest.DataTransferObject.PageResponse;
import com.cap.BookStroreRest.DataTransferObject.UserDto;
import com.cap.BookStroreRest.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register new user",
            description = "Creates a new user account in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user input")
    })
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login user",
            description = "Authenticates user using email and password"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginDto) {
        return ResponseEntity.ok(userService.loginUser(loginDto));
    }

    @GetMapping("/getAll")
    @Operation(
            summary = "Get all users",
            description = "Retrieves all registered users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    })
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    @Operation(
            summary = "Get user by ID",
            description = "Retrieves a specific user using their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update user",
            description = "Updates user information using ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserDto userDto) {

        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete user",
            description = "Deletes a user using their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/page")
    @Operation(
            summary = "Get users with pagination and sorting",
            description = "Fetch users using page number, size, sorting field and direction"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    })
    public ResponseEntity<PageResponse<UserDto>> getUsers(

            @Parameter(description = "Page number (starts from 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of records per page")
            @RequestParam(defaultValue = "5") int size,

            @Parameter(description = "Field to sort by (id, name, email)")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sorting direction: asc or desc")
            @RequestParam(defaultValue = "asc") String direction
    ) {

        return ResponseEntity.ok(
                userService.getUsers(page, size, sortBy, direction)
        );
    }
}