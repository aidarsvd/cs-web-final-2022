package pro.aidar.alatoonews.model.dto.user;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank private String name;
    @NotBlank private String surname;
    @NotBlank private String email;
    @NotBlank private String username;
    @NotBlank private String password;
    @NotBlank private String repeatPassword;
}
