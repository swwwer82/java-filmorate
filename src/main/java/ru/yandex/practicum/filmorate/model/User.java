package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.filmorate.annotation.NoWhiteSpaces;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    private Long id;

    @NotBlank(message = "email не может быть путсым")
    @Email(message = "Неверный формат для email")
    private String email;

    @NotBlank(message = "login не может быть пустым")
    @NoWhiteSpaces
    private String login;

    private String name;

    @PastOrPresent(message = "день рождения не может быть в будующем")
    private LocalDate birthday;

    private Set<Long> friends;
}
