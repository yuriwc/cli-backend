package com.yuricavalcante.challenge.cli;

import com.yuricavalcante.challenge.cli.analysis.TreeAnalyzer;
import com.yuricavalcante.challenge.cli.arguments.ArgumentParser;
import com.yuricavalcante.challenge.cli.logging.MetricsLogger;
import com.yuricavalcante.challenge.cli.model.Node;
import com.yuricavalcante.challenge.cli.utils.JsonTreeBuilder;
import com.yuricavalcante.challenge.cli.utils.TextUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CliApplication {

	public static void main(String[] args) {
		try {
			// Parse dos argumentos
			ArgumentParser parser = new ArgumentParser(args);

			// Carregar o JSON da árvore
			long startTime = System.currentTimeMillis();
			String jsonString = new String(Files.readAllBytes(Paths.get("src/main/java/com/yuricavalcante/challenge/cli/dicts/arvore.json")));
			Node tree = JsonTreeBuilder.buildFromJson(jsonString);
			long loadTime = System.currentTimeMillis() - startTime;

			// Analisar a árvore
			startTime = System.currentTimeMillis();
			List<String> words = TextUtils.transformarTextoParaLista(parser.getText());
			TreeAnalyzer analyzer = new TreeAnalyzer();
			Map<String, Integer> wordCount = analyzer.analyzeTree(tree, words, parser.getDepth());
			long checkTime = System.currentTimeMillis() - startTime;

			// Exibir resultados
			if (wordCount.isEmpty()) {
				System.out.println("Nenhuma palavra encontrada no nível " + parser.getDepth());
			} else {
				System.out.println(wordCount);
			}

			// Exibir métricas, se verbose
			if (parser.isVerbose()) {
				MetricsLogger.logPerformanceMetrics(loadTime, checkTime);
			}

		} catch (IOException e) {
			System.out.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
