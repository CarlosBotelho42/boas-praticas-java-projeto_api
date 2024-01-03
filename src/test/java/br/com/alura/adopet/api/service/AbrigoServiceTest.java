package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    private Abrigo abrigo;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;


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

    @Test
    void deveriaListarTodosOsAbrigos(){

        abrigoService.listar();

        then(abrigoRepository).should().findAll();

    }

    @Test
    void deveriaChamarListaDePetsDoAbrigo(){

      String nome = "Casa dos gatos";
      given(abrigoRepository.findByNome(nome)).willReturn(abrigo);

      abrigoService.listarPetsDoAbrigo(nome);

      then(petRepository).should().findByAbrigo(abrigo);

    }
}