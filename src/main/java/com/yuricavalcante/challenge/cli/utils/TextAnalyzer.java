package com.yuricavalcante.challenge.cli.utils;

import com.yuricavalcante.challenge.cli.model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextAnalyzer {
    private final Node transporte;
    private final int depth;

    public TextAnalyzer(Node transporte, int depth) {
        this.transporte = transporte;
        this.depth = depth;
    }

    public Map<String, Integer> analyzeText(String texto) {
        // Transformar o texto em uma lista de palavras
        List<String> palavras = TextUtils.transformarTextoParaLista(texto);

        // Lista para armazenar palavras encontradas na árvore
        List<String> palavrasEncontradas = new ArrayList<>();

        // Mapa para contar a ocorrência de palavras por nível
        Map<String, Integer> contadorPorNivel = new HashMap<>();

        // Loop para buscar cada palavra na árvore
        for (String palavra : palavras) {
            // Busca a palavra na árvore (caso exista um nó correspondente)
            Node noEncontrado = transporte.searchByTitle(transporte, palavra);
            if (noEncontrado != null) {
                // Busca o nó de nível superior (ou nível de profundidade desejado)
                Node pai = noEncontrado.searchParentAtDepth(noEncontrado, depth);

                // Se o nó pai for encontrado, atualize os dados
                if (pai != null) {
                    // Se o pai ainda não está na lista de palavras encontradas, adicione
                    if (!palavrasEncontradas.contains(pai.getTitle())) {
                        palavrasEncontradas.add(pai.getTitle());
                    }

                    // Incrementa o contador de palavras por nível
                    contadorPorNivel.put(pai.getTitle(), contadorPorNivel.getOrDefault(pai.getTitle(), 0) + 1);
                }
            }
        }

        return contadorPorNivel;
    }
}
