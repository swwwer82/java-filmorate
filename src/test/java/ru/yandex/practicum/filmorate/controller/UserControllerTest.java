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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddNewUserId1() throws Exception {
        String json = "{\"name\":\"John Doe\",\"login\":\"johndoe\",\"email\":\"john@example.com\"}";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAddNewUserId2() throws Exception {
        String json = "{\"name\":\"John Doa\",\"login\":\"johndoa\",\"email\":\"johna@example.com\"}";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAddNewUserId3() throws Exception {
        String json = "{\"name\":\"John Doas\",\"login\":\"johndoas\",\"email\":\"johnas@example.com\"}";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAddNewUserAsSomeId() throws Exception {
        String json = "{\"id\": 1,\"name\":\"John Doa\",\"login\":\"johndoa\",\"email\":\"johna@example.com\"}";

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isInternalServerError());
    }



    @Test
    void shouldFindAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindUserById2() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }


    @Test
    void shouldFindUserById1() throws Exception {
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindUserByIdNotCorrectId() throws Exception {
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddFriendToUser() throws Exception {
        mockMvc.perform(put("/users/1/friends/2"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetFriendsOfUser() throws Exception {
        mockMvc.perform(get("/users/1/friends"))
                .andExpect(status().isOk());
    }


    @Test
    void shouldGetMutualFriends() throws Exception {
        mockMvc.perform(get("/users/1/friends/common/3"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteFriendFromUser() throws Exception {
        mockMvc.perform(delete("/users/1/friends/2"))
                .andExpect(status().isOk());
    }
}
