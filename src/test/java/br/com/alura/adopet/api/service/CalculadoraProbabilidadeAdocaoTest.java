package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.PetsDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade de adocao alta para idade baixa e peso baixo")
    void deveriaRetornarAlta(){
        //idade 4 peso 3 kg = ALTA

        //ARRANGE
        Abrigo abrigo = new Abrigo(new AbrigoDTO(
                "Abrigo do carlao",
                "09999999999",
                "carlos@abrigo.com"
        ));

        Pet pet = new Pet(new PetsDTO(
                TipoPet.GATO,
                "Yummi",
                "vira lata",
                4,
                "CinzaBranco",
                3.0f
        ),abrigo);

        //ACT
        CalculadoraProbabilidadeAdocao calculadora =  new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade =  calculadora.calcular(pet);

        //ASSERT
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);

    }

    @Test
    @DisplayName("Probabilidade de adocao alta para idade alta e peso baixo")
    void deveriaRetornarMedia(){
        //idade 15 peso 4 kg = MEDIA

        Abrigo abrigo = new Abrigo(new AbrigoDTO(
                "Abrigo do carlao",
                "09999999999",
                "carlos@abrigo.com"
        ));

        Pet pet = new Pet(new PetsDTO(
                TipoPet.GATO,
                "Yummi",
                "vira lata",
                15,
                "CinzaBranco",
                4.0f
        ),abrigo);

        CalculadoraProbabilidadeAdocao calculadora =  new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade =  calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);

    }

    @Test
    @DisplayName("Probabilidade de adocao alta para idade alta e peso alto")
    void deveriaRetornarBaixa(){
        //idade 15 peso 11 kg = BAIXA

        Abrigo abrigo = new Abrigo(new AbrigoDTO(
                "Abrigo do carlao",
                "09999999999",
                "carlos@abrigo.com"
        ));

        Pet pet = new Pet(new PetsDTO(
                TipoPet.GATO,
                "Yummi",
                "vira lata",
                15,
                "CinzaBranco",
                11.0f
        ),abrigo);

        CalculadoraProbabilidadeAdocao calculadora =  new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade =  calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);

    }
}