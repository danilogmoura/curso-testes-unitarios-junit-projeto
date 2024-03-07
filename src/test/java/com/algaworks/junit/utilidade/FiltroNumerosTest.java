package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class FiltroNumerosTest {

    @Test
    public void deveRetornarNumerosPares() {

        var numeros = Arrays.asList(1, 2, 3, 4);
        var numerosParesEsperados = Arrays.asList(2, 4);

        var resultadoFiltro = FiltroNumeros.numerosPares(numeros);

        assertIterableEquals(numerosParesEsperados, resultadoFiltro);
    }

}