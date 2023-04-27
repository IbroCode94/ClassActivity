package com.example.classactivity.dto;

import lombok.*;


@AllArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String password;
}
