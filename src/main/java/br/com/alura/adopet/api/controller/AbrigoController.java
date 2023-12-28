package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.PetsDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private final AbrigoService abrigoService;

    private final PetService petService;

    public AbrigoController(AbrigoService abrigoService, PetService petService) {
        this.abrigoService = abrigoService;
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<AbrigoDTO>> listar() {
        return ResponseEntity.ok(abrigoService.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid AbrigoDTO dto) {

        try {
            this.abrigoService.cadastrar(dto);
            return ResponseEntity.ok("Cadastro realizado com sucesso");

        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<Pet>> listarPets(@PathVariable String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = repository.getReferenceById(id).getPets();
            return ResponseEntity.ok(pets);
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = repository.findByNome(idOuNome).getPets();
                return ResponseEntity.ok(pets);
            } catch (EntityNotFoundException enfe) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid PetsDTO dto) {

        try {
            Abrigo abrigo = abrigoService.carregarAbrigo(idOuNome);
            petService.cadastrarPet(abrigo, dto);
            return ResponseEntity.ok().build();

        } catch (ValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
