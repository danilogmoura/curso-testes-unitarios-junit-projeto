package com.algaworks.junit.blog.utilidade;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConversorSlugTest {

    @Test
    void deveConverterJuntoComCodigo() {
        try (var mockedStatic = Mockito.mockStatic(GeradorCodigo.class)) {
            mockedStatic.when(GeradorCodigo::gerar).thenReturn("123456");
            var slug = ConversorSlug.converterJuntoComCodigo("olá mundo java");
            assertEquals("ola-mundo-java-123456", slug);
        }
    }

}