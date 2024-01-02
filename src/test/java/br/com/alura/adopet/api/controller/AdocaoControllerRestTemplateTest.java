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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
class AdocaoControllerRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JacksonTester<SolicitacaoAdocaoDTO> jsonDto;

    @Test
    @DisplayName("Retorna codigo 400 para uma solicitação de adoção com erros")
    void deveriaRetornar400() throws Exception {

        String json = "{}";

        ResponseEntity<Void> response = restTemplate.exchange(
                "/adocoes",
                HttpMethod.POST,
                new HttpEntity<>(json),
                Void.class
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    @DisplayName("Retorna codigo 200 para uma solicitação de adoção sem erros")
    void deveriaRetornar200() throws Exception {

        SolicitacaoAdocaoDTO dto = new SolicitacaoAdocaoDTO(1l, 1l, "Nao sei... estou apenas testanto" );

        ResponseEntity<Void> response = restTemplate.exchange(
                "/adocoes",
                HttpMethod.POST,
                new HttpEntity<>(jsonDto.write(dto).getJson()),
                Void.class
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}