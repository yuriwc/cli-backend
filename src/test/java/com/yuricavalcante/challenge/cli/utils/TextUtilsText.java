package com.yuricavalcante.challenge.cli.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextUtilsText {
    @Test
    void testTransformarTextoParaListaComPontuacao() {
        String texto = "Ola, mundo! Como voce esta?";
        List<String> resultado = TextUtils.transformarTextoParaLista(texto);

        assertEquals(5, resultado.size());
        assertEquals(List.of("ola", "mundo", "como", "voce", "esta"), resultado);
    }

    @Test
    void testTransformarTextoParaListaComEspacosExtras() {
        String texto = "   Ola    mundo    ";
        List<String> resultado = TextUtils.transformarTextoParaLista(texto);

        assertEquals(2, resultado.size());
        assertEquals(List.of("ola", "mundo"), resultado);
    }

    @Test
    void testTransformarTextoParaListaVazio() {
        String texto = "";
        List<String> resultado = TextUtils.transformarTextoParaLista(texto);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void testTransformarTextoParaListaComCaracteresEspeciais() {
        String texto = "Texto #com @caracteres %especiais!";
        List<String> resultado = TextUtils.transformarTextoParaLista(texto);

        assertEquals(4, resultado.size());
        assertEquals(List.of("texto", "com", "caracteres", "especiais"), resultado);
    }

    @Test
    void testTransformarTextoParaListaMaiusculasMinusculas() {
        String texto = "Ola Mundo OLA MUNDO";
        List<String> resultado = TextUtils.transformarTextoParaLista(texto);

        assertEquals(4, resultado.size());
        assertEquals(List.of("ola", "mundo", "ola", "mundo"), resultado);
    }
}
