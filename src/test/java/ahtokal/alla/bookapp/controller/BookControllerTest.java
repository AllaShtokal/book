package ahtokal.alla.bookapp.controller;

import ahtokal.alla.bookapp.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void evaluateBookPageableParameter() throws Exception {
        mockMvc.perform(get("/api/book")
                        .param("page", "1")
                        .param("size", "2")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk());

    }
}