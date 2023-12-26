package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.PestDTO;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<PestDTO> listarTodosDisponiveis(){

        return petRepository
                .findAllByAdotadoFalse()
                .stream()
                .map(PestDTO::new)
                .toList();

    }
}
