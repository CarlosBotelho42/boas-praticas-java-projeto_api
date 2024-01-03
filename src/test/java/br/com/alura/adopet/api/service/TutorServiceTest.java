package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizarTutorDTO;
import br.com.alura.adopet.api.dto.TutorDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService service;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private TutorDTO dto;

    @Mock
    private Tutor tutor;

    @Mock
    private AtualizarTutorDTO atualizarTutorDTO;

    @Test
    void naoDeveriaCadastrarTutorComTelefoneOuEmailJaCadastrado(){

        given(tutorRepository.existsByTelefoneAndEmail(dto.telefone(), dto.email())).willReturn(true);

        assertThrows(ValidacaoException.class, () -> service.cadastrar(dto));

    }

    @Test
    void deveriaCadastrarTutor(){

        given(tutorRepository.existsByTelefoneAndEmail(dto.telefone(),dto.email())).willReturn(false);

        assertDoesNotThrow(() -> service.cadastrar(dto));
        then(tutorRepository).should().save(new Tutor(dto));

    }

    @Test
    void deveriaAtualizarTutor(){

        given(tutorRepository.getReferenceById(atualizarTutorDTO.id())).willReturn(tutor);

        service.atualizar(atualizarTutorDTO);

        then(tutor).should().atualizarDados(atualizarTutorDTO);

    }



}