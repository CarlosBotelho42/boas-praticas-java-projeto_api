package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.alura.adopet.api.model.StatusAdocao.AGUARDANDO_AVALIACAO;
import static br.com.alura.adopet.api.model.StatusAdocao.APROVADO;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidaTutorComLimiteAdocoesTest {

    @InjectMocks
    private ValidaTutorComLimiteAdocoes validacao;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private SolicitacaoAdocaoDTO dto;

    @Test
    void deveriaLiberarAdocaoTutorLimiteAdocoesNaoAtigindo(){

        BDDMockito.given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), APROVADO)).willReturn(4);

        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));

    }

    @Test
    void naoDeveriaLiberarAdocaoTutorLimiteAdocoesAtigindo(){

        BDDMockito.given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), APROVADO)).willReturn(5);

        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));

    }

}