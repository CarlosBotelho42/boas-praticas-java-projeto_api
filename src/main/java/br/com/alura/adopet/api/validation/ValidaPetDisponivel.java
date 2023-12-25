package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidaPetDisponivel implements ValidacaoSolicitacaoAdocao {

    private final PetRepository petRepository;

    public ValidaPetDisponivel(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void validar(SolicitacaoAdocaoDTO dto){

        Pet pet = petRepository.getReferenceById(dto.idPet());
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!");
        }
    }
}
