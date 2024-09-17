package com.yuricavalcante.challenge.cli.logging;

public class MetricsLogger {
    public static void logPerformanceMetrics(long loadTime, long checkTime) {
        System.out.println("\nMétricas de desempenho:");
        System.out.println("Tempo de carregamento dos parâmetros: " + loadTime + " ms");
        System.out.println("Tempo de verificação da frase: " + checkTime + " ms");
    }
}
