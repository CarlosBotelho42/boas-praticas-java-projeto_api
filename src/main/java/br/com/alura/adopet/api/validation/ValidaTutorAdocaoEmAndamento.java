package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidaTutorAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    private final AdocaoRepository adocaoRepository;
    private final TutorRepository tutorRepository;

    public ValidaTutorAdocaoEmAndamento(AdocaoRepository adocaoRepository, TutorRepository tutorRepository) {
        this.adocaoRepository = adocaoRepository;
        this.tutorRepository = tutorRepository;

    }

    public void validar(SolicitacaoAdocaoDTO dto) {

        boolean tutorAdocoesEmAndamento = adocaoRepository.existsByPetIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (tutorAdocoesEmAndamento) {
            throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
        }
    }
}
