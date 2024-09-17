# CLI de Análise Hierárquica

## Descrição
Este projeto é uma CLI (Command Line Interface) que realiza a análise hierárquica de frases e organiza-as em uma estrutura de árvore. O projeto foi desenvolvido em Java e permite a manipulação de estruturas de dados hierárquicas.

### Funcionalidades
- Criação de árvores hierárquicas baseadas em inputs textuais.
- Navegação e busca em árvores de nós.
- Suporte para leitura JSON com a estrutura da árvore.

### Estrutura do Projeto
O projeto é organizado da seguinte maneira:

- **dicts/**: Contém a definição da estrutura da árvore através de um exemplo JSON. O arquivo deve se chamar arvore.json para a correta leitura.
- **models/**: Contém as classes de modelo utilizadas na aplicação.
- **utils/**: Contém utilitários para manipulação de JSON e outras funcionalidades auxiliares.
- **Main.java**: O ponto de entrada da aplicação CLI.

## Requisitos

Para executar o projeto, você precisa ter instalado:

- Java JDK 11 ou superior
- Maven (ou outra ferramenta de build)
- Git (para controle de versão)

### Como Executar

1. Compile o projeto utilizando uma ferramenta de build como Maven.
   ```bash
   mvn clean install
Execute a CLI com o seguinte comando:

```bash
java -jar cli.jar analyze --depth 5 "Eu tenho preferência por animais carnívoros"
```
Neste exemplo:
--depth 5 define a profundidade máxima da árvore.
"Eu tenho preferência por animais carnívoros" é a frase a ser analisada.

### Exemplo de Saída
```
output: Pássaros = 1; Primatas = 1;
```


