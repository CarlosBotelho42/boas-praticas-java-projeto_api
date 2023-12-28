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

    public void cadastrarPet(String idOuNome, PetsDTO dto) {
        Pet pet = new Pet();

        try {
            Abrigo abrigo = abrigoRepositoy.getReferenceById(dto.id());
            cadastroDePetNoAbrigo(pet, abrigo);

        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = carregarAbrigo(idOuNome);
                cadastroDePetNoAbrigo(pet, abrigo);

            } catch (EntityNotFoundException e) {
                throw new ValidacaoException("Não foram encontrados abrigos com esse id ou nome");
            }
        }
    }

    private void cadastroDePetNoAbrigo(Pet pet, Abrigo abrigo) {
        pet.setAbrigo(abrigo);
        pet.setAdotado(false);
        abrigo.getPets().add(pet);
        abrigoRepositoy.save(abrigo);
    }

    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optional;

        try {
            Long id = Long.parseLong(idOuNome);
            optional = abrigoRepositoy.findById(id);

        } catch (NumberFormatException e){
            optional = Optional.ofNullable(abrigoRepositoy.findByNome(idOuNome));
        }

        return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
    }
}
