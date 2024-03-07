package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaTest {

    @Test
    void assercaoAgrupada() {
        var pessoa = new Pessoa("Danilo", "Moura");

//        assertEquals("Danilo", pessoa.getNome());
//        assertEquals("Moura", pessoa.getSobrenome());

        assertAll("Asseções de pessoa",
                () -> assertEquals("Danilo", pessoa.getNome()),
                () -> assertEquals("Moura", pessoa.getSobrenome()));
    }

}