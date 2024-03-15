package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.dao.MpaDao;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.FilmDBStorage;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
class FilmDBStorageTest {

    @Autowired
    private FilmDBStorage filmStorage;

    @Autowired
    private MpaDao mpaDao;

    @Test
    void testAddFilm() {
        // Проверяем добавление нового фильма
        Mpa mpa = mpaDao.findAll().get(0); // Получаем первый Mpa из базы данных
        Film newFilm = new Film(null, "New Film", "Description", LocalDate.now(), 120, new HashSet<>(), mpa, new HashSet<>());
        Film addedFilm = filmStorage.add(newFilm);

        // Проверяем, что фильм успешно добавлен и получил идентификатор
        Assertions.assertNotNull(addedFilm.getId());

        // Удаление добавленного фильма после теста
        filmStorage.remove(addedFilm.getId());
    }

    @Test
    void testUpdateFilm() {
        // Предварительно добавляем фильм для обновления
        Mpa mpa = mpaDao.findAll().get(0); // Получаем первый Mpa из базы данных
        Film filmToUpdate = new Film(null, "Update Film", "Description", LocalDate.now(), 120, new HashSet<>(), mpa, new HashSet<>());
        Film addedFilm = filmStorage.add(filmToUpdate);

        // Обновляем данные фильма
        addedFilm.setName("Updated Film");
        Film updatedFilm = filmStorage.update(addedFilm);

        // Проверяем, что данные фильма были успешно обновлены
        Assertions.assertEquals("Updated Film", updatedFilm.getName());

        // Удаление обновленного фильма после теста
        filmStorage.remove(updatedFilm.getId());
    }

    @Test
    void testRemoveFilm() {
        // Предварительно добавляем фильм для удаления
        Mpa mpa = mpaDao.findAll().get(0); // Получаем первый Mpa из базы данных
        Film filmToRemove = new Film(null, "Remove Film", "Description", LocalDate.now(), 120, new HashSet<>(), mpa, new HashSet<>());
        Film addedFilm = filmStorage.add(filmToRemove);

        // Удаляем фильм
        filmStorage.remove(addedFilm.getId());

        // Проверяем, что фильм был успешно удален
        Film removedFilm = filmStorage.findById(addedFilm.getId());
        Assertions.assertNull(removedFilm);
    }

    @Test
    void testFindAllFilms() {
        // Предварительно добавляем несколько фильмов
        Mpa mpa = mpaDao.findAll().get(0); // Получаем первый Mpa из базы данных
        Film film1 = new Film(null, "Film 1", "Description", LocalDate.now(), 120, new HashSet<>(), mpa, new HashSet<>());
        Film film2 = new Film(null, "Film 2", "Description", LocalDate.now(), 120, new HashSet<>(), mpa, new HashSet<>());
        filmStorage.add(film1);
        filmStorage.add(film2);

        // Получаем список всех фильмов
        List<Film> allFilms = filmStorage.findAll();

        // Проверяем, что в списке присутствуют добавленные фильмы
        Assertions.assertTrue(allFilms.stream().anyMatch(film -> film.getName().equals(film1.getName())));
        Assertions.assertTrue(allFilms.stream().anyMatch(film -> film.getName().equals(film2.getName())));

        // Удаляем добавленные фильмы после теста
        filmStorage.remove(film1.getId());
        filmStorage.remove(film2.getId());
    }
}
