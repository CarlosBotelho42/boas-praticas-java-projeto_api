package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public void cadastrar(TutorDTO dto) {

        boolean verificaCadastro = tutorRepository.existsByTelefoneAndEmail(dto.telefone(), dto.email());

        if (verificaCadastro) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }

        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        tutorRepository.save(tutor);

    }


}
