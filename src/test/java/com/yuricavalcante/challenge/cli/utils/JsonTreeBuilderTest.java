package com.yuricavalcante.challenge.cli.utils;

import com.yuricavalcante.challenge.cli.model.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTreeBuilderTest {

    @Test
    void testBuildFromJsonWithSingleLevel() {
        String jsonString = "{\"key1\": {}, \"key2\": {}}";
        Node root = JsonTreeBuilder.buildFromJson(jsonString);

        assertNotNull(root);
        assertEquals("Arvore Hierarquica", root.getTitle());
        assertEquals(2, root.getChildren().size());
        assertEquals("key1", root.getChildren().get(0).getTitle());
        assertEquals("key2", root.getChildren().get(1).getTitle());
    }

    @Test
    void testBuildFromJsonWithMultipleLevels() {
        String jsonString = "{\"key1\": {\"subkey1\": {}}, \"key2\": {\"subkey2\": {}}}";
        Node root = JsonTreeBuilder.buildFromJson(jsonString);

        assertNotNull(root);
        assertEquals("Arvore Hierarquica", root.getTitle());
        assertEquals(2, root.getChildren().size());

        Node key1 = root.getChildren().getFirst();
        assertEquals("key1", key1.getTitle());
        assertEquals(1, key1.getChildren().size());
        assertEquals("subkey1", key1.getChildren().getFirst().getTitle());

        Node key2 = root.getChildren().get(1);
        assertEquals("key2", key2.getTitle());
        assertEquals(1, key2.getChildren().size());
        assertEquals("subkey2", key2.getChildren().getFirst().getTitle());
    }

    @Test
    void testBuildFromJsonWithEmptyJson() {
        String jsonString = "{}";
        Node root = JsonTreeBuilder.buildFromJson(jsonString);

        assertNotNull(root);
        assertEquals("Arvore Hierarquica", root.getTitle());
        assertTrue(root.getChildren().isEmpty());
    }

    @Test
    void testBuildFromJsonWithInvalidJson() {
        String jsonString = "{\"key1\": \"value\"}";
        Node root = JsonTreeBuilder.buildFromJson(jsonString);

        assertNotNull(root);
        assertEquals("Arvore Hierarquica", root.getTitle());
        assertEquals(1, root.getChildren().size());
        assertEquals("key1", root.getChildren().getFirst().getTitle());
        assertTrue(root.getChildren().getFirst().getChildren().isEmpty());  // Não tem filhos
    }

}
