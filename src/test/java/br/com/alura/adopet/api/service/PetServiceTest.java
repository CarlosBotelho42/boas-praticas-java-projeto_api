package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.PetsDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService service;

    @Mock
    private PetRepository petRepository;

    @Mock
    private Abrigo abrigo;

    @Mock
    private PetsDTO dto;

    @Test
    void deveriaCadastrarPet(){

       service.cadastrarPet(abrigo, dto);

       then(petRepository).should().save(new Pet(dto, abrigo));

    }

    @Test
    void deveriaRetornarTodosPetsDisponiveis(){

        service.listarTodosDisponiveis();

        then(petRepository).should().findAllByAdotadoFalse();

    }

}