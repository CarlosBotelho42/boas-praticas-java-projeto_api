package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetsDTO(
        Long id,
        @Enumerated(EnumType.STRING)
        @NotNull
        TipoPet tipo,


        @NotBlank
        String nome,

        @NotBlank
        String raca,

        @NotBlank
        Integer idade,

        @NotBlank
        String cor,

        @NotNull
        Float peso) {

    public PetsDTO(Pet pet){
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso());
    }
}
