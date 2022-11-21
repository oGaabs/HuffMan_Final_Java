import java.util.*;

import static java.util.Objects.requireNonNull;

public class Huffman {
    private No raiz;
    private final String texto;
    private Map<Character, Integer> charFrequencias;
    private final Map<Character, String> huffmanCodes;

    public Huffman(String texto) {
        this.texto = texto;
        fillFrequenciasDosCaracteres();
        huffmanCodes = new HashMap<>();
    }

    private void fillFrequenciasDosCaracteres() {
        charFrequencias = new HashMap<>();
        for (char caractere : texto.toCharArray()) {
            charFrequencias.put(caractere, charFrequencias.getOrDefault(caractere, 0) + 1);
        }
    }

    public String codificar() {

        Queue<No> fila = new PriorityQueue<>(); // Adiciona os elementos em uma fila ordenada automaticamente
        charFrequencias.forEach((character, frequency) ->
                fila.add(new Folha(character, frequency))  // Adiciona cada caracter (Primeiro as folhas) e sua frequencia na fila
        );
        while (fila.size() > 1) {
            // Adiciona para cada dois caracteres adiciona os elementos em No e adiona na fila
            fila.add(new No(fila.poll(), requireNonNull(fila.poll())));
        }
        gerarHuffmanCodes(raiz = fila.poll(), "");

        return getTextoCodificado();
    }

    private void gerarHuffmanCodes(No node, String code) {
        if (node instanceof Folha folha) {
            huffmanCodes.put(folha.getCaracter(), code);
            return;
        }
        gerarHuffmanCodes(node.getEsquerda(), code.concat("0"));
        gerarHuffmanCodes(node.getDireita(), code.concat("1"));
    }

    private String getTextoCodificado() {
        StringBuilder ret = new StringBuilder();
        for (char character : texto.toCharArray()) {
            ret.append(huffmanCodes.get(character));
        }
        return ret.toString();
    }

    public String decodificar(String encodedText) {
        StringBuilder ret = new StringBuilder();
        No atual = raiz;
        for (char character : encodedText.toCharArray()) {
            atual = character == '0' ? atual.getEsquerda() : atual.getDireita();
            if (atual instanceof Folha folha) {
                ret.append(folha.getCaracter());
                atual = raiz;
            }
        }
        return ret.toString();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        huffmanCodes.forEach((character, code) -> ret.append(character).append(": ").append(code).append("\n"));
        return ret.toString();
    }
}