package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    private Long id;

    @NotBlank(message = "email не может ьыть путсым")
    @Email(message = "Неверный формат для email")
    private String email;

    private String name;

    @PastOrPresent(message = "день рождения не может быть в будующем")
    private LocalDate birthDate;


}
