package com.yuricavalcante.challenge.cli.arguments;

public class ArgumentParser {
    private boolean verbose;
    private final int depth;
    private final String text;

    public ArgumentParser(String[] args) {
        if (args.length < 3 || !args[0].equals("analyze") || !args[1].equals("--depth")) {
            throw new IllegalArgumentException("Uso correto: java -jar cli.jar analyze --depth <número> \"<texto>\" [--verbose]");
        }
        this.depth = Integer.parseInt(args[2]);
        this.text = args[3];

        if (args.length == 5 && args[4].equals("--verbose")) {
            this.verbose = true;
        }
    }

    public int getDepth() {
        return depth;
    }

    public String getText() {
        return text;
    }

    public boolean isVerbose() {
        return verbose;
    }
}
