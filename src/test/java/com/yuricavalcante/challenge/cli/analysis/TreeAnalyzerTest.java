package com.yuricavalcante.challenge.cli.analysis;

import com.yuricavalcante.challenge.cli.model.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TreeAnalyzerTest {

    private TreeAnalyzer treeAnalyzer;
    private Node root;

    @BeforeEach
    public void setup() {
        treeAnalyzer = new TreeAnalyzer();

        // Criar uma árvore de exemplo
        root = new Node("Root");
        Node level1 = new Node("Level1");
        Node level2 = new Node("Level2");

        root.addChild(level1);
        level1.addChild(level2);

        // Adicionando filhos
        Node childA = new Node("A");
        Node childB = new Node("B");
        level2.addChild(childA);
        level2.addChild(childB);
    }

    @Test
    public void testAnalyzeTree_FoundWordsAtDepth() {
        // Lista de palavras a serem encontradas na árvore
        List<String> words = Arrays.asList("A", "B");

        // Profundidade que desejamos analisar
        int depth = 2;

        // Chamada do método
        Map<String, Integer> result = treeAnalyzer.analyzeTree(root, words, depth);

        // Verificações
        assertEquals(2, result.get("Level2"));  // A e B estão ambos no Level2, então a contagem deve ser 2
        assertEquals(1, result.size());  // Apenas Level2 deve estar no mapa, já que ambos A e B estão nesse nível
    }


    @Test
    public void testAnalyzeTree_NoWordsFoundAtDepth() {
        List<String> words = List.of("C"); // Palavra não existente
        int depth = 2;

        Map<String, Integer> result = treeAnalyzer.analyzeTree(root, words, depth);

        // Deve estar vazio, pois "C" não está na árvore
        assertTrue(result.isEmpty());
    }
}
