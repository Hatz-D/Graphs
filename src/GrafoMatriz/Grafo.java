/*
Diogo Lourenzon Hatz     - 10402406
Nicolas Fernandes Melnik - 10402170

Classe: Classe abstrata das classes de grafo, utilizada para polimorfismo

Mudanças:
- Versão inicial da main da aplicação, como menu de opções - Diogo Hatz, 10/09/2024
- Separação das classes de grafo em classes distintas e criação de classe abstrata Grafos- Diogo Hatz, 11/09/2024
- Criação da classe Projeto para métodos de manipulação de arquivos - Diogo Hatz, 11/09/2024
- Garantir consistência dos métodos das classes dos grafos - Nicolas Melnik 13/09/2024
*/

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

    public int getIndexFromName(String name) {
        for(int i = 0; i < n; ++i) {
            if(nomes[i].equals(name)) {return i;}
        }

        return -1;
    }

    public String getNameFromIndex(int i) {return nomes[i];}

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
