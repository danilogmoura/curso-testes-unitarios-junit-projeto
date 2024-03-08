package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FiltroNumerosTest {

    // Given, When, Then
    // Dado, Quando, Então

    @Test
    public void Dado_uma_lista_de_numeros_Quando_filtrar_por_pares_Entao_deve_retornar_apenas_numeros_pares() {

        var numeros = Arrays.asList(1, 2, 3, 4);
        var numerosParesEsperados = Arrays.asList(2, 4);
        var resultadoFiltro = FiltroNumeros.numerosPares(numeros);
        assertIterableEquals(numerosParesEsperados, resultadoFiltro);
    }

    @Test
    public void Dado_uma_lista_de_numeros_Quando_filtrar_por_impares_Entao_deve_retornar_apenas_numeros_impares() {
        var numeros = Arrays.asList(1, 2, 3, 4);
        var numerosImparesEsperados = Arrays.asList(1, 3);
        var resultadoFiltro = FiltroNumeros.numerosImpares(numeros);
        assertIterableEquals(numerosImparesEsperados, resultadoFiltro);
    }

}