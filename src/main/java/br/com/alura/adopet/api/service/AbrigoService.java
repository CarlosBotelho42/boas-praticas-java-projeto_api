package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.PetsDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    private final AbrigoRepository abrigoRepositoy;

    private final PetRepository petRepository;

    public AbrigoService(AbrigoRepository abrigoRepositoy, PetRepository petRepository) {
        this.abrigoRepositoy = abrigoRepositoy;
        this.petRepository = petRepository;
    }

    public void cadastrar(AbrigoDTO dto) {

        boolean abrigoVerificaNomeTelefoneEemail = abrigoRepositoy.existsByNomeAndTelefoneAndEmail(dto.nome(), dto.telefone(), dto.email());

        if (abrigoVerificaNomeTelefoneEemail) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }

        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        abrigoRepositoy.save(abrigo);

    }

    public List<AbrigoDTO> listar() {
        return abrigoRepositoy
                .findAll()
                .stream()
                .map(AbrigoDTO::new)
                .toList();
    }

    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optional;
        try {
            Long id = Long.parseLong(idOuNome);
            optional = abrigoRepositoy.findById(id);

        } catch (NumberFormatException e) {
            optional = Optional.ofNullable(abrigoRepositoy.findByNome(idOuNome));
        }

        return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
    }

    public List<PetsDTO> listarPetsDoAbrigo(String idOuNome) {

        Abrigo abrigo = carregarAbrigo(idOuNome);

        return petRepository
                .findByAbrigo(abrigo)
                .stream()
                .map(PetsDTO::new)
                .toList();
    }
}
