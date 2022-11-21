public class Folha extends No {
    private final char caracter;

    public char getCaracter() {
        return caracter;
    }

    public Folha(char caracter, int frequencia) {
        super(frequencia);
        this.caracter = caracter;
    }
}