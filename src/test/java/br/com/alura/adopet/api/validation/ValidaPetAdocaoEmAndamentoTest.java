package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.alura.adopet.api.model.StatusAdocao.AGUARDANDO_AVALIACAO;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidaPetAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidaPetAdocaoEmAndamento validacao;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDTO dto;

    @Test
    void naoDeveriaPermitirAdocaoEmAndamento(){

        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), AGUARDANDO_AVALIACAO)).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));

    }

    @Test
    void deveriaPermitirAdocaoEmAndamento(){

        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), AGUARDANDO_AVALIACAO)).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));

    }

}