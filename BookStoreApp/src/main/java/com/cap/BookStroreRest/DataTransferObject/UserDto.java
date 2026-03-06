package com.cap.BookStroreRest.DataTransferObject;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "Name is requried")
    private String name;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6,message = "Password should be of atleast 6 character")
    private String password;
}
