package dev.patika.librarymanagementapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import dev.patika.librarymanagementapi.repositories.AuthorRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthorApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void clearAuthors() {
        authorRepository.deleteAll();
    }

    @Test
    void createsAndReadsAnAuthorThroughThePublicApi() throws Exception {
        mockMvc.perform(post("/api/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "name": "Ursula K. Le Guin",
                                          "birthDate": 1929,
                                          "country": "United States"
                                        }
                                        """))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.name").value("Ursula K. Le Guin"));

        mockMvc.perform(get("/api/authors"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Ursula K. Le Guin"))
               .andExpect(jsonPath("$[0].books").isArray());
    }

    @Test
    void reportsMissingAuthorsAsNotFound() throws Exception {
        mockMvc.perform(get("/api/authors/404"))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.message").value("Entity not found"));
    }

    @Test
    void rejectsBlankAuthorNamesAsBadRequests() throws Exception {
        mockMvc.perform(post("/api/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "name": "  ",
                                          "birthDate": 1929,
                                          "country": "United States"
                                        }
                                        """))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.message").value("Validation error"));
    }
}
