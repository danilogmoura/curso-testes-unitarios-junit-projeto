package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.algaworks.junit.utilidade.SaudacaoUtil.saudar;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SaudacaoUtilTest {


    @Test
    public void Dado_um_horario_matuino_Quando_saudaer_Entao_deve_retornar_bom_dia() {
        // Arrange
        var horaValida = 5;

        // Act
        var saudacao = saudar(horaValida);

        // Assert
        // assertEquals("Bom dia", saudacao);

        // String saudacaoCorreta = "Bom dia";

        // var bomDia = new Condition<>((s) -> s.equals(saudacaoCorreta), "igual a %s", saudacaoCorreta);

        assertThat(saudacao)
                // .as("Validando se a saudação é %s", saudacaoCorreta)
                // .withFailMessage("Erro: Saudação incorreta! Resultado: %s", saudacao)
                // .isEqualTo(saudacaoCorreta);
                .is(SaudacaoUtilConditions.igualBomDia());
    }

    @Test
    void Dado_um_horario_vespertino_Quando_saudar_Entao_deve_retornar_boa_tarde() {
        var horaValida = 15;
        var saudacao = saudar(horaValida);
        assertEquals("Boa tarde", saudacao);
    }

    @Test
    void Dado_um_horario_noturno_Quando_saudar_Entao_deve_retornar_boa_noite() {
        var horaValida = 4;
        var saudacao = saudar(horaValida);
        assertEquals("Boa noite", saudacao);
    }

    @Test
    public void Dado_uma_hora_invalida_Quando_saudar_Entao_deve_lancar_exception() {
        int horaInvalida = -10;
        // Executable chamaInvalidaDeMetodo = () -> saudar(horaInvalida);
        // var exception = assertThrows(IllegalArgumentException.class, chamaInvalidaDeMetodo);
        // assertEquals("Hora inválida", exception.getMessage());

        // var exception = Assertions.catchThrowableOfType(() -> saudar(horaInvalida), IllegalArgumentException.class);
        // assertThat(exception).hasMessage("Hora inválida");

        assertThatThrownBy(() -> saudar(horaInvalida))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Hora inválida");
    }

    @Test
    public void Dado_uma_hora_valida_Quanto_saudar_Entao_deve_lancar_exception() {
        var horaValida = 0;
        Executable chamadaValidaDeMetodo = () -> saudar(horaValida);
        assertDoesNotThrow(chamadaValidaDeMetodo);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9, 10, 11})
    public void Dado_horario_matinal_Quando_saudar_Entao_deve_retornar_bom_dia(int horaValida) {
        var saudacao = saudar(horaValida);
        assertEquals("Bom dia", saudacao);
    }

}