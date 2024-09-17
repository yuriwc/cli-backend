package com.yuricavalcante.challenge.cli.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    @Test
    public void testNodeCreation() {
        Node node = new Node("Meios de Transporte");
        assertEquals("Meios de Transporte", node.getTitle());
        assertTrue(node.getChildren().isEmpty());
    }

    @Test
    public void testAddChild() {
        Node parent = new Node("Animal");
        Node child = new Node("Mamífero");

        parent.addChild(child);

        assertEquals(1, parent.getChildren().size());
        assertEquals(child, parent.getChildren().getFirst());
        assertEquals(parent, child.getParent());
    }

    @Test
    public void testGetDepth() {
        Node root = new Node("Animal");
        Node mammal = new Node("Mamífero");
        Node dog = new Node("Cachorro");

        root.addChild(mammal);
        mammal.addChild(dog);

        assertEquals(0, root.getDepth());
        assertEquals(1, mammal.getDepth());
        assertEquals(2, dog.getDepth());
    }

    @Test
    public void testSearchByTitle() {
        Node root = new Node("Animal");
        Node mammal = new Node("Mamífero");
        Node dog = new Node("Cachorro");

        root.addChild(mammal);
        mammal.addChild(dog);

        assertEquals(dog, root.searchByTitle(root, "Cachorro"));
        assertNull(root.searchByTitle(root, "Peixe"));
    }

    @Test
    public void testSearchParentAtDepth() {
        Node root = new Node("Animal");
        Node mammal = new Node("Mamífero");
        Node dog = new Node("Cachorro");

        root.addChild(mammal);
        mammal.addChild(dog);

        assertEquals(mammal, dog.searchParentAtDepth(dog, 1));
        assertEquals(root, dog.searchParentAtDepth(dog, 0));
        assertNull(dog.searchParentAtDepth(dog, 3)); // Não existe nó nessa profundidade
    }
}
