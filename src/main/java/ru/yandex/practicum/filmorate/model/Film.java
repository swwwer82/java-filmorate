package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yandex.practicum.filmorate.annotation.ValidReleaseDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
public class Film {
    private Long id;

    @NotBlank(message = "Не может быть пустым")
    private String name;

    @Size(min = 1, max = 200, message = "Максимальная длина 200 символов")
    private String description;

    @ValidReleaseDate
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма не должна быть отрицательной")
    private int duration;

    private Set<Long> likes;

}
