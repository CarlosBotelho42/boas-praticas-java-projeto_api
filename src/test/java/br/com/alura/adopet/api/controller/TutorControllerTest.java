package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class TutorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TutorService tutorService;

    @Test
    void deveriaRetornar200ParaCadastroDoTutor() throws Exception {

        String json = """
                {
                    "nome": "Carlos",
                    "telefone": "34999999999",
                    "email": "carlos@gmail.com"
                }
                """;

        MockHttpServletResponse response = mvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());

    }

//    @Test
//    void deveriaRetornar400ParaCadastroDoTutor() throws Exception {
//
//        String json = """
//                {
//                    "nome": "Carlos",
//                    "telefone": "(34)9999-99999",
//                    "email": "carlos@gmail.com"
//                }
//                """;
//
//        MockHttpServletResponse response = mvc.perform(
//                post("/tutores")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andReturn().getResponse();
//
//        assertEquals(400,response.getStatus());
//
//    }

}