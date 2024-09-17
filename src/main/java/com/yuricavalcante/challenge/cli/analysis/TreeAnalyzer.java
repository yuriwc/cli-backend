package com.yuricavalcante.challenge.cli.analysis;

import com.yuricavalcante.challenge.cli.model.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeAnalyzer {
    public Map<String, Integer> analyzeTree(Node tree, List<String> words, int depth) {
        Map<String, Integer> wordCountByLevel = new HashMap<>();

        for (String word : words) {
            Node foundNode = tree.searchByTitle(tree, word);
            if (foundNode != null) {
                Node parentNode = foundNode.searchParentAtDepth(foundNode, depth);
                if (parentNode != null) {
                    wordCountByLevel.put(parentNode.getTitle(),
                            wordCountByLevel.getOrDefault(parentNode.getTitle(), 0) + 1);
                }
            }
        }

        return wordCountByLevel;
    }
}
