package com.yuricavalcante.challenge.cli.utils;

import com.yuricavalcante.challenge.cli.model.Node;
import org.json.JSONObject;

public class JsonTreeBuilder {

    public static Node buildFromJson(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        Node root = new Node("Arvore Hierarquica");
        buildTree(root, jsonObject);
        return root;
    }

    private static void buildTree(Node parent, JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            Node childNode = new Node(key);
            parent.addChild(childNode);

            // Se o valor for um objeto, continuar construindo a árvore
            if (value instanceof JSONObject) {
                JSONObject childObject = (JSONObject) value;

                // Só continuar se o objeto não for vazio (tem filhos)
                if (childObject.length() > 0) {
                    buildTree(childNode, childObject);
                }
            }
        }
    }
}
