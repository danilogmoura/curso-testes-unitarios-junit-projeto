package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    @Test
    public void saudarBomDiaTest() {
        var horaValida = 9;
        var saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Bom dia", saudacao);
    }

    @Test
    public void saudarBomDiaTestApartirDasCincoHoras() {
        var horaValida = 5;
        var saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Bom dia", saudacao);
    }

    @Test
    void deveSaudarBoaTardeTest() {
        var horaValida = 15;
        var saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa tarde", saudacao);
    }

    @Test
    void deveSaudarBoaNoite() {
        var horaValida = 20;
        var saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa noite", saudacao);
    }

    @Test
    void deveSaudarBoaNoiteAsQuatroHoras() {
        var horaValida = 4;
        var saudacao = SaudacaoUtil.saudar(horaValida);
        assertEquals("Boa noite", saudacao);
    }

    @Test
    public void deveLancarExceptionTest() {
        var horaInvalida = -10;
        var illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> SaudacaoUtil.saudar(horaInvalida));
        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }

    @Test
    public void naoDeveLancarException() {
        var horaValida = 0;
        assertDoesNotThrow(() -> SaudacaoUtil.saudar(horaValida));
    }

}