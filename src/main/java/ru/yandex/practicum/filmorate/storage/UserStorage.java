package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
  User add(User user);

  User update(User user);

  void remove(Long id);

  List<User> findAll();

  User findById(Long id);
}
