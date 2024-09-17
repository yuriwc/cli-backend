package com.yuricavalcante.challenge.cli.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String title;
    private Node parent;
    private final List<Node> children;

    // Construtor
    public Node(String title) {
        this.title = title;
        this.children = new ArrayList<>();  // Inicializa a lista de filhos
    }

    // Retorna o título do nó
    public String getTitle() {
        return title;
    }

    // Retorna o nó pai
    public Node getParent() {
        return parent;
    }

    // Define o nó pai
    public void setParent(Node parent) {
        this.parent = parent;
    }

    // Retorna a lista de filhos
    public List<Node> getChildren() {
        return children;
    }

    // Adiciona um filho ao nó
    public void addChild(Node child) {
        child.setParent(this);   // Define este nó como pai do filho
        this.children.add(child);  // Adiciona o filho à lista de filhos
    }


    public int getDepth() {
        int depth = 0;
        Node node = this;
        while (node.getParent() != null) {
            depth++;
            node = node.getParent();
        }
        return depth;
    }

    //Pesquisa um nó com um título específico
    public Node searchByTitle(Node current, String title) {
        if (current.getTitle().equalsIgnoreCase(title)) {
            return current;  // Encontrou o nó com o título correspondente
        }

        for (Node child : current.getChildren()) {
            Node result = searchByTitle(child, title);  // Pesquisa recursiva nos filhos
            if (result != null) {
                return result;
            }
        }
        return null;  // Não encontrou o nó
    }

    // Pesquisar o pai em um nível específico
    public Node searchParentAtDepth(Node current, int depth) {
        if (current == null) {
            return null;  // Se o nó atual for nulo, retorna null
        }
        if (current.getDepth() == depth) {
            return current;  // Encontrou o nó pai no nível especificado
        }
        // Se a profundidade atual for maior que a desejada, continua subindo
        if (depth < current.getDepth()) {
            return searchParentAtDepth(current.getParent(), depth);  // Pesquisa recursiva no pai
        } else {
            return null;  // Não encontrou o nó pai no nível especificado
        }
    }
}
