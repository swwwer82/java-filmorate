package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserDBStorage;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
class UserDBStorageTest {

    @Autowired
    private UserDBStorage userStorage;

    @Test
    void testAddUser() {
        // Проверяем добавление нового пользователя
        User newUser = new User(null, "newuser@example.com", "new_user_login", "New User", LocalDate.now(), new HashSet<>());
        User addedUser = userStorage.add(newUser);

        // Проверяем, что пользователь успешно добавлен и получил идентификатор
        Assertions.assertNotNull(addedUser.getId());

        // Удаление добавленного пользователя после теста
        userStorage.remove(addedUser.getId());
    }

    @Test
    void testUpdateUser() {
        // Предварительно добавляем пользователя для обновления
        User userToUpdate = new User(null, "update@example.com", "update_login", "Update User", LocalDate.now(), new HashSet<>());
        User addedUser = userStorage.add(userToUpdate);

        // Обновляем данные пользователя
        addedUser.setName("Updated Name");
        User updatedUser = userStorage.update(addedUser);

        // Проверяем, что данные пользователя были успешно обновлены
        Assertions.assertEquals("Updated Name", updatedUser.getName());

        // Удаление обновленного пользователя после теста
        userStorage.remove(updatedUser.getId());
    }

    @Test
    void testRemoveUser() {
        // Предварительно добавляем пользователя для удаления
        User userToRemove = new User(null, "remove@example.com", "remove_login", "Remove User", LocalDate.now(), new HashSet<>());
        User addedUser = userStorage.add(userToRemove);

        // Удаляем пользователя
        userStorage.remove(addedUser.getId());

        // Проверяем, что пользователь был успешно удален
        User removedUser = userStorage.findById(addedUser.getId());
        Assertions.assertNull(removedUser);
    }

    @Test
    void testFindAllUsers() {
        // Предварительно добавляем несколько пользователей
        User user1 = new User(null, "user1@example.com", "user1_login", "User 1", LocalDate.now(), new HashSet<>());
        User user2 = new User(null, "user2@example.com", "user2_login", "User 2", LocalDate.now(), new HashSet<>());
        userStorage.add(user1);
        userStorage.add(user2);

        // Получаем список всех пользователей
        List<User> allUsers = userStorage.findAll();

        // Проверяем, что в списке присутствуют добавленные пользователи
        Assertions.assertTrue(allUsers.stream().anyMatch(user -> user.getEmail().equals(user1.getEmail())));
        Assertions.assertTrue(allUsers.stream().anyMatch(user -> user.getEmail().equals(user2.getEmail())));

        // Удаляем добавленных пользователей после теста
        userStorage.remove(user1.getId());
        userStorage.remove(user2.getId());
    }
}
