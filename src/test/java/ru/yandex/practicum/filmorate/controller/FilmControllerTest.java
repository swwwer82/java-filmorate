package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureMockMvc
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateNewFilm() throws Exception {
        String json = "{\"name\":\"nisi eiusmod\",\"description\":\"adipisicing\",\"releaseDate\":\"1967-03-25\",\"duration\":100}";

        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewFilmWithNegativeDuration() throws Exception {
        String json = "{\"name\":\"nisi eiusmod\",\"description\":\"adipisicing\",\"releaseDate\":\"1967-03-25\",\"duration\":-100}";

        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateNewFilmWithEmptyName() throws Exception {
        String json = "{\"name\":\"\",\"description\":\"adipisicing\",\"releaseDate\":\"1967-03-25\",\"duration\":100}";

        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateNewFilmWithReleaseDateBeforeTheDayOfTheFilm() throws Exception {
        String json = "{\"name\":\"nisi eiusmod\",\"description\":\"adipisicing\",\"releaseDate\":\"1867-03-25\",\"duration\":100}";

        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateNewFilmInWithNameOfMoreThan200Characters() throws Exception {
        String json = "{\n" +
                "  \"name\": \"nisi eiusmo\",\n" +
                "  \"description\": \"adipisicing dssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaassssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss\",\n" +
                "  \"releaseDate\": \"1967-03-25\",\n" +
                "  \"duration\": 100\n" +
                "}";
        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFindAllFilms() throws Exception {
        mockMvc.perform(get("/films"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindFilmByNotCorrectId() throws Exception {
        mockMvc.perform(get("/films/9999"))
                .andExpect(status().isNotFound());
    }
    @Test
    void shouldFindFilmById() throws Exception {
        mockMvc.perform(get("/films/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAddLikeToFilm() throws Exception {
        // Assuming film with ID 1 and user ID 1 already exists
        mockMvc.perform(put("/films/1/like/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetPopularFilms() throws Exception {
        mockMvc.perform(get("/films/popular"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteLikeFromFilm() throws Exception {
        mockMvc.perform(delete("/films/1/like/1"))
                .andExpect(status().isOk());
    }
}
