package com.yuricavalcante.challenge.cli.utils;

public class CommandLineArgs {
    private final int depth;
    private final String texto;
    private final boolean verbose;

    public CommandLineArgs(int depth, String texto, boolean verbose) {
        this.depth = depth;
        this.texto = texto;
        this.verbose = verbose;
    }

    public int getDepth() {
        return depth;
    }

    public String getTexto() {
        return texto;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public static CommandLineArgs parseArgs(String[] args) {
        if (args.length < 3 || !args[0].equals("analyze") || !args[1].equals("--depth")) {
            throw new IllegalArgumentException("Uso correto: java -jar cli.jar analyze --depth <número> \"<texto>\" [--verbose]");
        }

        int depth = Integer.parseInt(args[2]);
        String texto = args[3];
        boolean verbose = args.length == 5 && args[4].equals("--verbose");

        return new CommandLineArgs(depth, texto, verbose);
    }
}
