public class Arvore {
    private No raiz;
    private int grau;

    public Arvore(No r) {
        raiz = r;
    }

    public Arvore() {
        this(null);
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No r) {
        raiz = r;
    }

    public int getGrau() {
        return grau;
    }

    public void setGrau(int g) {
        grau = g;
    }

    private void inserir(No R, No e) throws Exception {
        if (e == null)
            throw new Exception("Elemento invalido");
        if (R == null) this.raiz = e;
        else {
            if (R.getFrequencia() < e.getFrequencia()) {
                if (R.getEsquerda() == null)
                    R.setEsquerda(e);
                else
                    inserir(R.getEsquerda(), e);
            } else {
                if (R.getDireita() == null)
                    R.setDireita(e);
                else
                    inserir(R.getDireita(), e);
            }
        }
    }

    //inverter uma arvore binaria
    public void inverterArvore(No R) {
        if (R == null) {
            return;
        }
        No aux = R.getDireita();
        R.setDireita(R.getEsquerda());
        R.setEsquerda(aux);
        inverterArvore(R.getDireita());
        inverterArvore(R.getEsquerda());
    }

    //retorne uma arvore nvertida
    public Arvore invertArvore() {
        Arvore arvoreInvertida = new Arvore();
        arvoreInvertida.setRaiz(this.raiz);
        arvoreInvertida.inverterArvore(arvoreInvertida.getRaiz());
        return arvoreInvertida;
    }


    //comparar se duas arvores sao iguais
    public boolean comparaArvore(No R1, No R2) {
        if (R1 == null && R2 == null) {
            return true;
        }
        if (R1 == null || R2 == null) {
            return false;
        }
        if (R1.getFrequencia() != R2.getFrequencia()) {
            return false;
        }
        return comparaArvore(R1.getDireita(), R2.getDireita()) && comparaArvore(R1.getEsquerda(), R2.getEsquerda());
    }

    //verifique se duas arvores passadas sao iguais e retorne true ou false
    public boolean comparaArvore(Arvore arvore) {
        return comparaArvore(this.raiz, arvore.getRaiz());
    }

    public void inserir(No e) throws Exception {
        inserir(this.raiz, e);
    }

    public void inserir(Character e) throws Exception {
        inserir(new No(e));
    }

    public String percoreInfixo(No n, int grau) {
        if (n == null) return "";


        return percoreInfixo(n.getDireita(), grau + 1) + " " +
                n.getFrequencia() + "(" + grau + ")" + " " +
                percoreInfixo(n.getEsquerda(), grau + 1);
    }

    public int altura(No R) {
        if (R == null) return 0;
        if ((R.getDireita() == null) &&
                (R.getEsquerda() == null)) return 0;

        int aDir = altura(R.getDireita());
        int aEsq = altura(R.getEsquerda());

        if (aDir > aEsq) return aDir + 1;
        return aEsq + 1;
    }

    private int acGrauEsquerda(No n, int grau) {
        if (n.getEsquerda().getEsquerda() == null)
            return grau + 1;

        return acGrauEsquerda(n.getEsquerda(), grau + 1);
    }

    private int acGrauDireira(No n, int grau) {
        return acGrauDireira(n.getEsquerda(), grau + 1);
    }

    public int qtasFolhas() {
        return qtasF(this.raiz);
    }

    private int qtasF(No R) {
        if (R == null) return 0;
        if ((R.getDireita() == null) && (R.getEsquerda() == null))
            return 1;

        return qtasF(R.getDireita()) + qtasF(R.getEsquerda());
    }

    public String toString() {
        return "[" + percoreInfixo(raiz, 0) + "]";
    }
}