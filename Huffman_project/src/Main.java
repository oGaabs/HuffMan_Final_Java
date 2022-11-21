import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {


        // Foi passado mais de um argumento para o programa
        /*if (args.length != 2) {
            System.err.println("Uso esperado: java Huffman <ArqEntrada> <ArqSaida>\n");
            return;
        }

        int cont = 0;
        for (String a : args )
            System.out.println("args["+cont++ +"] " + a);*/


        String nomeDaEntrada = FileSystems.getDefault().getPath("src/Arquivos/" + "entrada.txt").toAbsolutePath().toString();
        //String nomeDaSaida = FileSystems.getDefault().getPath("src/Arquivos/" + "saida.txt").toAbsolutePath().toString();

        FileInputStream arquivofisico = new FileInputStream(nomeDaEntrada);
        BufferedInputStream buffReader = new BufferedInputStream(arquivofisico);
        try (DataInputStream data = new DataInputStream(buffReader)) {
            byte[] vetByte;
            vetByte = new byte[arquivofisico.available()];

            data.read(vetByte);

            String texto = new String(vetByte);
            System.out.println("Original:" + texto);
            Huffman huffman = new Huffman(texto);
            String textoCodificado = huffman.codificar();
            System.out.println("Codigos das letras:\n" + huffman);
            System.out.println("Codificado:" + textoCodificado);

            String textoDescodificado = huffman.decodificar(textoCodificado);
            System.out.println("Decodificado:" + textoDescodificado);
        } catch (Error e) {
        }
    }
}