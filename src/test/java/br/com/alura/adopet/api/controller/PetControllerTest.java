package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AbrigoService abrigoService;

    @MockBean
    private PetService petService;

    @Test
    void deveriaRetornar200ParaListarPertsDisponiveis() throws Exception {

        MockHttpServletResponse response = mvc.perform(
                get("/pets")
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());

    }

    @Test
    void deveriaRetornar400ParaListarPertsDisponiveis() throws Exception {

        MockHttpServletResponse response = mvc.perform(
                get("/")
        ).andReturn().getResponse();

        assertEquals(404,response.getStatus());

    }

}