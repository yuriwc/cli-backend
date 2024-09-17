package com.yuricavalcante.challenge.cli.arguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgumentParserTest {

    @Test
    public void testValidArgumentsWithoutVerbose() {
        String[] args = {"analyze", "--depth", "3", "Texto de exemplo"};
        ArgumentParser parser = new ArgumentParser(args);

        assertEquals(3, parser.getDepth());  // Verifica se a profundidade é 3
        assertEquals("Texto de exemplo", parser.getText());  // Verifica se o texto é correto
        assertFalse(parser.isVerbose());  // Verifica que o verbose não foi ativado
    }

    @Test
    public void testValidArgumentsWithVerbose() {
        String[] args = {"analyze", "--depth", "5", "Outro exemplo", "--verbose"};
        ArgumentParser parser = new ArgumentParser(args);

        assertEquals(5, parser.getDepth());  // Verifica se a profundidade é 5
        assertEquals("Outro exemplo", parser.getText());  // Verifica se o texto é correto
        assertTrue(parser.isVerbose());  // Verifica que o verbose foi ativado
    }

    @Test
    public void testInvalidArgumentsThrowsException() {
        String[] args = {"invalid", "--depth", "3", "Texto de exemplo"};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ArgumentParser(args);
        });

        assertEquals("Uso correto: java -jar cli.jar analyze --depth <número> \"<texto>\" [--verbose]", exception.getMessage());
    }

    @Test
    public void testMissingArgumentsThrowsException() {
        String[] args = {"analyze", "--depth"};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ArgumentParser(args);
        });

        assertEquals("Uso correto: java -jar cli.jar analyze --depth <número> \"<texto>\" [--verbose]", exception.getMessage());
    }
}
