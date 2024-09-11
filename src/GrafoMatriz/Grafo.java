package GrafoMatriz;

public abstract class Grafo {
    protected int n; // quantidade de vértices
    protected int m; // quantidade de arestas
    protected String[] nomes;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public String[] getNomes() {
        return nomes;
    }

    public void setNomes(String[] nomes) {
        this.nomes = nomes;
    }

    public void show() {}

    public void insereV() {}

    public void insereV(String nome) {}

    public void insereV(double peso, String nome) {}

    public void removeV(int v) {}

    public void insereA(int v1, int v2) {}

    public void insereA(int v1, int v2, double peso) {}

    public void removeA(int v1, int v2) {}

    public int conectividadade() {return -1;}
}
