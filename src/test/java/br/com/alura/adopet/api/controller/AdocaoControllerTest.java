package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdocaoService adocaoService;


    @Test
    @DisplayName("Retorna codigo 400 para uma solicitação de adoção com erros")
    void deveriaRetornar400() throws Exception {

        String json = "{}";

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());

    }

    @Test
    @DisplayName("Retorna codigo 200 para uma solicitação de adoção sem erros")
    void deveriaRetornar200() throws Exception {

        String json = """
                {
                    "idPet" : 1,
                    "idTutor" : 1,
                    "motivo" : "Nao sei... estou apenas testanto"
                }
                """;

        var response = mvc.perform(
                MockMvcRequestBuilders
                        .post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());

    }

}