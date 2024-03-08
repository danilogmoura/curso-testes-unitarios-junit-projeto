package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Conta Bancária")
public class ContaBancariaBDDTest {

    @Nested
    @DisplayName("Dado uma conta bancária com saldo de R$10,00")
    class ContaBancariComSaldo {

        private ContaBancaria contaBancaria;

        @BeforeEach
        void beforeEach() {
            contaBancaria = new ContaBancaria(BigDecimal.TEN);
        }

        @Nested
        @DisplayName("Quando efetuar o saque com valor menor")
        class SaqueValorMenor {

            private final BigDecimal valorSaque = new BigDecimal("9.0");

            @Test
            @DisplayName("Então não deve lançar exception")
            void naoDeveLancarException() {
                assertDoesNotThrow(() -> contaBancaria.saque(valorSaque));
            }

            @Test
            @DisplayName("E deve subtrair do saldo")
            void deveSubtrairDoSaldo() {
                contaBancaria.saque(valorSaque);
                assertEquals(new BigDecimal("1.0"), contaBancaria.saldo());
            }
        }

        @Nested
        @DisplayName("Quando efetuar o saque com valor maior")
        class SaqueComValorMaior {

            private final BigDecimal valorSaque = new BigDecimal("20.0");

            @Test
            @DisplayName("Então deve lançar exception")
            void deveLancarException() {
                assertThrows(RuntimeException.class, () -> contaBancaria.saque(valorSaque));
            }

            @Test
            @DisplayName("E não deve alterar o saldo")
            void naoDeveAlterarSaldo() {
                try {
                    contaBancaria.saque(valorSaque);
                } catch (Exception ingore) {
                }
                assertEquals(BigDecimal.TEN, contaBancaria.saldo());
            }
        }
    }

    @Nested
    @DisplayName("Dado uma conta bancária com saldo de R$0,00")
    class ContaBancariaSemSaldo {

        private ContaBancaria contaBancaria;

        @BeforeEach
        void beforeEach() {
            contaBancaria = new ContaBancaria(BigDecimal.ZERO);
        }

        @Nested
        @DisplayName("Quando efetuar um deposito de R$8,00")
        class efetuarDeposito {

            @Test
            @DisplayName("Então deve somar ao saldo")
            void deveSomaSaldo() {
                contaBancaria.deposito(new BigDecimal("8.00"));
            }
        }

        @Nested
        @DisplayName("Quando efetuar o saque com valor maior")
        class EfetuarSaqueValorMaior {

            private final BigDecimal valorSaque = new BigDecimal("0.1");

            @Test
            @DisplayName("Então deve lançar exception")
            void deeLancarException() {
                assertThrows(RuntimeException.class, () -> contaBancaria.saque(new BigDecimal("0.1")));
            }

            @Test
            @DisplayName("E saldo não deve ser alterado")
            void saldoNaoDeveSerAlterado() {
                try {
                    contaBancaria.saque(new BigDecimal("0.1"));
                } catch (Exception ignora) {
                }

                assertEquals(BigDecimal.ZERO, contaBancaria.saldo());
            }
        }

    }
}
