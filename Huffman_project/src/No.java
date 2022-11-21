public class No implements Comparable<No> {
    private int frequencia = 0;
    private No direita;
    private No esquerda;

    public No(No d, No e) {
        this.direita = d;
        this.esquerda = e;
        this.frequencia = esquerda.getFrequencia() + direita.getFrequencia();
    }


    protected No(int frequencia) {
        this.direita = null;
        this.esquerda = null;
        this.frequencia = frequencia;
    }

    public No(int frequencia, Object o, Object o1) {
    }

    protected int getFrequencia() {
        return this.frequencia;
    }

    protected void setFrequencia(int freq) {
        this.frequencia = freq;
    }

    public void setDireita(No d) {
        direita = d;
    }

    public void setEsquerda(No e) {
        esquerda = e;
    }

    public No getDireita() {
        return direita;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public boolean isFolha() {
        return esquerda == null && direita == null;
    }

    @Override
    public int compareTo(No no) {
        return Integer.compare(frequencia, no.getFrequencia());
    }

    public String toString() {
        return "" + frequencia;
    }
}
