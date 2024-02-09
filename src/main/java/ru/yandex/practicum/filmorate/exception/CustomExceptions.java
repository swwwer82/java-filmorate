package ru.yandex.practicum.filmorate.exception;

public class CustomExceptions {
    public static class FilmException extends RuntimeException {
        public FilmException(String error) {
            super(error);
        }
    }

    public static class UserException extends RuntimeException {
        public UserException(String error) {
            super(error);
        }
    }
}
