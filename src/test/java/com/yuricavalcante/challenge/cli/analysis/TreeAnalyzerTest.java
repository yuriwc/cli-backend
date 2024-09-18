package com.yuricavalcante.challenge.cli.analysis;

import com.yuricavalcante.challenge.cli.model.Node;
import com.yuricavalcante.challenge.cli.utils.TextUtils;
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

    @Test
    public void testAnalyzeTextWithLargeInput() {
        // Texto real com mais de 5000 caracteres
        String longText = """
                Na vasta extensão da savana, os animais carnívoros e herbívoros coexistem em um equilíbrio delicado.
                Os carnívoros, como leões e tigres, caçam suas presas com precisão. Eles são predadores formidáveis,
                movendo-se furtivamente pela grama alta, à espreita de qualquer sinal de movimento. Quando a caça
                começa, seus músculos ágeis e fortes se contraem em uma explosão de velocidade e poder, uma dança
                mortal que define a sobrevivência na natureza. Herbívoros, por outro lado, como zebras e antílopes,
                pastam em grupos, sempre atentos ao perigo. Seus corpos são construídos para velocidade e resistência,
                fugindo rapidamente ao menor indício de ameaça.

                As plantas, por sua vez, fornecem sustento para esses herbívoros. Árvores frutíferas, com suas copas
                altas, abrigam deliciosos frutos que caem no chão, prontos para serem consumidos. Esses frutos não
                apenas alimentam os herbívoros, mas também desempenham um papel crucial no ciclo de vida das plantas,
                dispersando suas sementes por toda a savana. As gramíneas, por outro lado, são a base da cadeia alimentar,
                fornecendo alimento para uma variedade de animais, desde insetos até elefantes. Sua presença é vital para
                a sobrevivência de todos os seres vivos na savana.
             
                A savana é um ecossistema complexo, onde cada ser vivo desempenha um papel fundamental. A interação entre
                predadores e presas, herbívoros e plantas, cria um equilíbrio delicado que sustenta a vida na região. A
                savana é um lugar de beleza e perigo, onde a natureza se desdobra em toda a sua glória e crueldade. É um
                lembrete de que, apesar de toda a nossa tecnologia e conhecimento, ainda somos apenas uma pequena parte
                de um mundo muito maior, um mundo que devemos respeitar e proteger.
                """;

        // Convertendo o texto em uma lista de palavras usando TextUtils
        List<String> words = TextUtils.transformarTextoParaLista(longText);

        // Chamada do método
        Map<String, Integer> result = treeAnalyzer.analyzeTree(root, words, 1);

        // Verificações
        assertEquals(words.size(), 268);  // Deve haver 176 palavras no texto
        assertEquals(words.getFirst(), "na");  // A primeira palavra deve ser "na"
        assertEquals(words.getLast(), "proteger");  // A última palavra deve ser "proteger"
        assertTrue(result.containsKey("Level1"));
        assertEquals(1, result.size());

        Node level1 = root.getChildren().get(0);
        assertEquals(level1.getTitle(), "Level1");
    }
}
