package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AbrigoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AbrigoService abrigoService;

    @MockBean
    private PetService petService;


    @Test
    void deveriaRetornar200ParaListarAbrigos() throws Exception {
        //ACT
        MockHttpServletResponse response = mvc.perform(
                get("/abrigos")
        ).andReturn().getResponse();

        //ASSERT
        assertEquals(200,response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaCadastrarAbrigo() throws Exception {

        String json = """
                {
                    "nome": "petz",
                    "telefone": "(34)9090-9090",
                    "email": "petz@testando.com.br"
                }
                """;

        MockHttpServletResponse response = mvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaCadastrarAbrigo() throws Exception{

        String json = """
                {
                 
                }
                """;

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());

    }

    @Test
    void deveriaRetornar200ParaListarPetsAbrigo() throws Exception{

        String idOuNome = "petz";

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/abrigos/{idOuNome}/pets", idOuNome)
        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());

    }

    @Test
    void deveriaRetornar400ParaListarPetsAbrigo() throws Exception{

        String idOuNome = "";

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .get("/abrigos/{idOuNome}/pets", idOuNome)
        ).andReturn().getResponse();

        Assertions.assertEquals(404, response.getStatus());

    }

    @Test
    void deveriaRetornar200ParaCadastrarPetsNoAbrigo() throws Exception {

        String json = """
                {
                    "tipo": "GATO",
                    "nome": "Luke",
                    "raca": "vira lata",
                    "idade": 4,
                    "cor": "Preto",
                    "peso": 6.3
                }
                """;

        String idOuNome = "petz";

        MockHttpServletResponse response = mvc.perform(
                post("/abrigos/{idOuNome}/pets", idOuNome)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200,response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaCadastrarPetsNoAbrigo() throws Exception {

        String json = """
                {
                
                }
                """;

        String idOuNome = "ptez";

        MockHttpServletResponse response = mvc.perform(
                post("/abrigos/{idOuNome}/pets", idOuNome)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400,response.getStatus());
    }

}