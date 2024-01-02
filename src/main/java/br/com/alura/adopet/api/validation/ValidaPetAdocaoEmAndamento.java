package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.stereotype.Component;


@Component
public class ValidaPetAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;

    public ValidaPetAdocaoEmAndamento(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;

    }

    public void validar(SolicitacaoAdocaoDTO dto) {

        boolean petTemAdocaoemandamento = adocaoRepository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO);

        if (petTemAdocaoemandamento) {
                throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
