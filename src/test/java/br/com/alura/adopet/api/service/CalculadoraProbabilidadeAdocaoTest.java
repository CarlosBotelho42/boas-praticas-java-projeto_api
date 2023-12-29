package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.PetsDTO;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    void cenario01(){
        //idade 4 peso kg = ALTA

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

        CalculadoraProbabilidadeAdocao calculadora =  new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade =  calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);

    }

    @Test
    void cenario02(){
        //idade 15 peso 4kg = MEDIA

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
    void cenario03(){
        //idade 15 peso 11kg = BAIXA

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