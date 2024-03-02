package ru.yandex.practicum.filmorate.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> idToFilm = new HashMap<>();

    @Override
    public Film add(Film film) {
        idToFilm.put(film.getId(), film);
        return film;
    }

    @Override
    public Film update(Film film) {
        idToFilm.put(film.getId(), film);
        return film;
    }

    @Override
    public void remove(Long id) {
        idToFilm.remove(id);
    }

    @Override
    public List<Film> findAll() {
        return new ArrayList<>(idToFilm.values());
    }

    @Override
    public Film findById(Long id) {
        return idToFilm.get(id);
    }

}