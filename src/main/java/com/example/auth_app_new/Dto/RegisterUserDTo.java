package com.example.auth_app_new.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class RegisterUserDTo {

    @NotBlank(message="fullname cannot empty")
    @Size(min=6, max=100,message="fullname error")
    String fullname;

    @NotBlank(message="password cannot empty")
    @Size(min=6, max=100,message="password error")
    String password;

    @Pattern(regexp="^[0-9]{12}$",message="phone is bad")
    @NotBlank(message="phone cannot empty")
    @Size(min=12, max=12,message="phone error")
    String phone; 

}
