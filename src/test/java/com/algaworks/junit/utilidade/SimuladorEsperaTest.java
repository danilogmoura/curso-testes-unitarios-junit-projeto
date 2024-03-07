package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class SimuladorEsperaTest {

    @Test
//    @Disabled("Não é mais aplicável")
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "DEV")
    public void deveEsperarENaoDarTimeOut() {
//        Assumptions.assumeTrue("PROD".equals(System.getenv("ENV")), () -> "Abortando teste: Não deve ser executado em PROD");

        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
//        assertTimeout(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofSeconds(10)));
    }
}