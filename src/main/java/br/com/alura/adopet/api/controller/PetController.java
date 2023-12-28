package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.PetsDTO;
import br.com.alura.adopet.api.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<PetsDTO>> listarTodosDisponiveis() {

        List<PetsDTO> list = petService.listarTodosDisponiveis();

        return ResponseEntity.ok(list);
    }
}
