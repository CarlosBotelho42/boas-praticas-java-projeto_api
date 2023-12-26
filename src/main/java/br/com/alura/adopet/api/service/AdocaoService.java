package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDTO;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.ValidacaoSolicitacaoAdocao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {


    private final AdocaoRepository repository;
    private final EmailService emailService;

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    private final List<ValidacaoSolicitacaoAdocao> validacoes;

    public AdocaoService(AdocaoRepository repository, EmailService emailService, PetRepository petRepository, TutorRepository tutorRepository, List<ValidacaoSolicitacaoAdocao> validacoes) {
        this.repository = repository;
        this.emailService = emailService;
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
        this.validacoes = validacoes;
    }

    public void solicitar(SolicitacaoAdocaoDTO dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor());

        validacoes.forEach(v -> v.validar(dto));

        Adocao adocao = new Adocao(tutor, pet, dto.motivo());

        repository.save(adocao);

        emailService.enviarEmail(
                adocao.getPet().getAbrigo().getEmail(),
                "Solicitação de adoção",
                "Olá " + adocao.getPet().getAbrigo().getNome() + "!Uma solicitação de adoção foi registrada hoje para o pet: " + adocao.getPet().getNome() + ". Favor avaliar para aprovação ou reprovação.");
    }

    public void aprovar(AprovacaoAdocaoDTO dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.marcarComoAprovada();

        emailService.enviarEmail(
                adocao.getTutor().getNome(),
                "Adoção aprovada",
                "Olá " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi aprovada. Favor entrar em contato com o abrigo " + adocao.getPet().getAbrigo().getNome() + " para agendar a busca do seu pet.");
    }

    public void reprovar(ReprovacaoAdocaoDTO dto) {
        Adocao adocao = repository.getReferenceById(dto.idAdocao());
        adocao.marcarComoReprovada(dto.justificativa());

        emailService.enviarEmail(
                adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá " + adocao.getTutor().getNome() + "!Infelizmente sua adoção do pet " + adocao.getPet().getNome() + ", solicitada em " + adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ", foi reprovada pelo abrigo " + adocao.getPet().getAbrigo().getNome() + " com a seguinte justificativa: " + adocao.getJustificativaStatus());
    }
}
