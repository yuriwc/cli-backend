package com.yuricavalcante.challenge.cli.logging;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricsLoggerTest {

    @Test
    public void testLogPerformanceMetrics() {
        // Captura a saída do System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Valores de teste
        long loadTime = 1234;
        long checkTime = 5678;

        // Chama o método a ser testado
        MetricsLogger.logPerformanceMetrics(loadTime, checkTime);

        // Restaura o System.out original
        System.setOut(originalOut);

        // Obtém a saída capturada
        String output = outputStream.toString().trim();

        // Corrige o formato esperado
        String expectedOutput = String.format(
                "Métricas de desempenho:%n" +
                        "Tempo de carregamento dos parâmetros: %d ms%n" +
                        "Tempo de verificação da frase: %d ms",
                loadTime, checkTime).trim();

        // Verifica se a saída está correta
        assertEquals(expectedOutput, output);
    }
}
