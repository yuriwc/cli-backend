package com.yuricavalcante.challenge.cli;

import com.yuricavalcante.challenge.cli.analysis.TreeAnalyzer;
import com.yuricavalcante.challenge.cli.arguments.ArgumentParser;
import com.yuricavalcante.challenge.cli.logging.MetricsLogger;
import com.yuricavalcante.challenge.cli.model.Node;
import com.yuricavalcante.challenge.cli.utils.TextUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CliApplicationTest {

    @Test
    public void testCliApplication() {
        // Inicia mocks necessários para os testes
        MockitoAnnotations.openMocks(this);

        // Mock do JSON que será carregado
        String jsonString = """
                {
                  "title": "root",
                  "children": [
                    {
                      "title": "child1",
                      "children": [
                        {
                          "title": "child1.1",
                          "children": []
                        }
                      ]
                    },
                    {
                      "title": "child2",
                      "children": []
                    }
                  ]
                }""";

        // Mock do ArgumentParser que simula os argumentos de entrada
        String[] args = {"analyze", "--depth", "1", "child1"};
        ArgumentParser parser = mock(ArgumentParser.class);
        when(parser.getText()).thenReturn("child1");  // Simula o texto inserido
        when(parser.getDepth()).thenReturn(1);        // Simula a profundidade configurada
        when(parser.isVerbose()).thenReturn(true);    // Configura o modo verboso

        // Mock da lista de palavras analisadas pelo TextUtils
        List<String> words = List.of("child1");
        try (MockedStatic<TextUtils> textUtilsMock = mockStatic(TextUtils.class)) {
            textUtilsMock.when(() -> TextUtils.transformarTextoParaLista(anyString())).thenReturn(words);

            // Mock do TreeAnalyzer que simula a análise da árvore
            TreeAnalyzer analyzer = mock(TreeAnalyzer.class);
            Map<String, Integer> wordCount = Map.of("child1", 1);
            when(analyzer.analyzeTree(any(Node.class), anyList(), anyInt())).thenReturn(wordCount);

            // Mock do MetricsLogger que simula a exibição de métricas
            MetricsLogger metricsLogger = mock(MetricsLogger.class);

            // Mock do Files que simula a leitura de um arquivo JSON
            try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
                filesMock.when(() -> Files.readAllBytes(any())).thenReturn(jsonString.getBytes());

                // Chama o método modificado da aplicação para realizar o teste
                CliApplication.runApplication(args, analyzer);

                // Verifica se os métodos estão sendo chamados corretamente
                textUtilsMock.verify(() -> TextUtils.transformarTextoParaLista("child1"), times(1)); // Verifica se o TextUtils foi chamado 1 vez
                verify(analyzer, times(1)).analyzeTree(any(Node.class), anyList(), anyInt()); // Verifica se a análise foi feita 1 vez
                verify(metricsLogger, times(1)); // Verifica se o logger foi chamado
                MetricsLogger.logPerformanceMetrics(anyLong(), anyLong()); // Simula o log das métricas de performance
            }
        }
    }
}
