package com.yuricavalcante.challenge.cli;

import com.yuricavalcante.challenge.cli.model.Node;
import com.yuricavalcante.challenge.cli.utils.JsonTreeBuilder;
import com.yuricavalcante.challenge.cli.utils.TextUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CliApplication {

	public static void main(String[] args) {
		long startTime, endTime;

		// Verifica se os argumentos estão corretos e se a opção verbose foi passada
		boolean verbose = false;
		if (args.length < 3 || !args[0].equals("analyze") || !args[1].equals("--depth")) {
			System.out.println("Uso correto: java -jar cli.jar analyze --depth <número> \"<texto>\" [--verbose]");
			System.exit(1);
		}

		if (args.length == 5 && args[4].equals("--verbose")) {
			verbose = true;
		}

		// Captura os valores dos parâmetros
		int depth = Integer.parseInt(args[2]);
		String texto = args[3];

		// Marca o tempo de início do carregamento dos parâmetros
		startTime = System.currentTimeMillis();

		// Carregar o JSON de um arquivo
		Node transporte = null;
		try {
			// Lê o JSON do arquivo localizado em src/main/resources/transporte.json
			String jsonString = new String(Files.readAllBytes(Paths.get("src/main/java/com/yuricavalcante/challenge/cli/dicts/arvore.json")));
			transporte = JsonTreeBuilder.buildFromJson(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Marca o tempo de fim do carregamento dos parâmetros
		endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;

		// Marca o tempo de início da verificação da frase
		startTime = System.currentTimeMillis();

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

		// Marca o tempo de fim da verificação da frase
		endTime = System.currentTimeMillis();
		long checkTime = endTime - startTime;

		// Exibe o mapa com as contagens por nível
		if (contadorPorNivel.isEmpty())
			System.out.println("Na frase não existe nenhum filho do nível " + depth + " e nem o nível " + depth + " possui os termos especificados.");
		else
			System.out.println("\n" + contadorPorNivel);

		// Exibe métricas se verbose for verdadeiro
		if (verbose) {
			System.out.println("\nMétricas de desempenho:");
			System.out.println("Tempo de carregamento dos parâmetros: " + loadTime + " ms");
			System.out.println("Tempo de verificação da frase: " + checkTime + " ms");
		}
	}
}
