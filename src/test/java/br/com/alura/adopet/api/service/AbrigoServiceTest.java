package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService abrigoService;

    @Mock
    private AbrigoDTO dto;

    @Mock
    private AbrigoRepository abrigoRepository;


    @Test
    void deveCadastrarAbrigoComSucesso() throws Exception {

        this.dto = new AbrigoDTO("testeAbrigo","34999999999", "teste@abrigo.com" );

        when(abrigoRepository.existsByNomeAndTelefoneAndEmail(dto.nome(), dto.telefone(), dto.email())).thenReturn(false);

        abrigoService.cadastrar(dto);

        Mockito.verify(abrigoRepository).save(Mockito.any(Abrigo.class));

    }

   @Test
    void deveLancarExcecaoQuandoDadosJaCadastrados(){

        this.dto = new AbrigoDTO("testeAbrigo","34999999999", "teste@abrigo.com" );

        when(abrigoRepository.existsByNomeAndTelefoneAndEmail(dto.nome(), dto.telefone(), dto.email())).thenReturn(true);

        assertThrows(ValidacaoException.class, () -> abrigoService.cadastrar(dto));

    }
}