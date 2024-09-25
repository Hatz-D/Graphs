/*
Diogo Lourenzon Hatz     - RA: 10402406
Eduardo Marui de Camargo - RA: 10400734
Nicolas Fernandes Melnik - RA: 10402170

Classe: Classe relativa ao grafo direcionado com peso nos vértices e arestas

Mudanças:
- Versão inicial da main da aplicação, como menu de opções - Diogo Hatz, 10/09/2024
- Separação das classes de grafo em classes distintas e criação de classe abstrata Grafos- Diogo Hatz, 11/09/2024
- Criação da classe Projeto para métodos de manipulação de arquivos - Diogo Hatz, 11/09/2024
- Garantir consistência dos métodos das classes dos grafos - Nicolas Melnik 13/09/2024
- Adicionando mapeamento da europa no arquivo grafo.txt - Nicolas Melnik e Eduardo Marui 24/09/2024
*/

package GrafoMatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TGrafoPesoRotulado extends Grafo {
    protected double[][] adj; // matriz de adjacência
    protected double[] pesos;

    // Métodos Públicos
    public TGrafoPesoRotulado(int n) { // construtor
        this.n = n;
        // No início dos tempos não há arestas
        this.m = 0;
        // alocação da matriz do TGrafo
        this.adj = new double[n][n];
        this.pesos = new double[n];
        this.nomes = new String[n];

        // Inicia a matriz com infinitos
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = Double.POSITIVE_INFINITY;
            }
            this.pesos[i] = Double.POSITIVE_INFINITY;
        }
    }

    public TGrafoPesoRotulado(double[][] adj, double[] pesos) {
        this.adj = adj;
        this.n = adj.length;
        this.pesos = pesos;
        this.nomes = new String[n];
        int count = 0;

        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj[i].length; j++) {
                if (adj[i][j] != Double.POSITIVE_INFINITY) {
                    count++;
                }
            }
        }

        this.m = count;
    }

    public double[][] getAdj() {
        return adj;
    }

    public void setAdj(double[][] adj) {
        this.adj = adj;
    }

    public double[] getPesos() {
        return pesos;
    }

    public void setPesos(double[] pesos) {
        this.pesos = pesos;
    }

    // Insere uma aresta no Grafo tal que
    // v é adjacente a w
    public void insereA(int v, int w, double p) {
        // testa se nao temos a aresta
        if (adj[v][w] == Double.POSITIVE_INFINITY) {
            adj[v][w] = p;
            m++; // atualiza qtd arestas
        }

        // else{System.out.println("\nAresta já existente!");}
    }

    // remove uma aresta v->w do Grafo
    public void removeA(int v, int w) {
        // testa se temos a aresta
        if (adj[v][w] != Double.POSITIVE_INFINITY) {
            adj[v][w] = Double.POSITIVE_INFINITY;
            m--; // atualiza qtd arestas
        }

        else {
            System.out.println("\nAresta não existente!");
        }
    }

    // Apresenta o Grafo contendo
    // número de vértices, arestas
    // e a matriz de adjacência obtida
    public void show() {
        System.out.println("V: " + n);
        System.out.println("A: " + m);
        for (int i = 0; i < n; i++) {
            System.out.print("\n");
            for (int w = 0; w < n; w++)
                if (adj[i][w] != Double.POSITIVE_INFINITY)
                    System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
                else
                    System.out.print("Adj[" + i + "," + w + "]= ∞" + " ");
        }
        System.out.println("\n\nfim da impressao do grafo.\n\nImpressão dos nomes:");

        for (int i = 0; i < nomes.length; ++i) {
            System.out.print(i + ": " + nomes[i] + ", ");
        }

        System.out.println("Impressão dos pesos dos vértices:");

        for (int i = 0; i < n; ++i) {
            System.out.print(i + ": " + pesos[i] + " - ");
        }
    }

    public int inDegree(int v) {
        int count = 0;
        for (int i = 0; i < adj.length; ++i) {
            if (adj[i][v] != Double.POSITIVE_INFINITY) {
                count++;
            }
        }

        return count;
    }

    public int outDegree(int v) {
        int count = 0;
        for (int i = 0; i < adj[v].length; ++i) {
            if (adj[v][i] != Double.POSITIVE_INFINITY) {
                count++;
            }
        }

        return count;
    }

    public int degree(int v) {
        return inDegree(v) + outDegree(v);
    }

    public int isFonte(int v) {
        int in = inDegree(v);
        int out = outDegree(v);
        return (out > 0 && in == 0) ? 1 : 0;
    }

    public int isSorvedouro(int v) {
        int in = inDegree(v);
        int out = outDegree(v);
        return (in > 0 && out == 0) ? 1 : 0;
    }

    public int isSimetrico() {
        for (int i = 0; i < adj.length; ++i) {
            for (int j = i + 1; j < adj[i].length; j++) {
                if (adj[i][j] != adj[j][i]) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public int isSimetrico(int[][] adj) {
        for (int i = 0; i < adj.length; ++i) {
            for (int j = i + 1; j < adj[i].length; j++) {
                if (adj[i][j] != adj[j][i]) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public static double[][] arquivo(String nome_arquivo) {
        File file = new File(nome_arquivo);
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        }

        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontado!");
            return null;
        }

        int v = Integer.parseInt(scanner.nextLine().strip());
        int a = Integer.parseInt(scanner.nextLine().strip());

        double[][] adj = new double[v][v];

        for (int i = 0; i < a; ++i) {
            String[] array = scanner.nextLine().strip().split(" ");
            int prim = Integer.parseInt(array[0]);
            int sec = Integer.parseInt(array[1]);
            double peso = Double.parseDouble(array[2]);

            adj[prim][sec] = peso;
        }

        return adj;
    }

    public void removeV(int v) {
        if (v > n - 1) {
            System.out.println("Vértice nao existente!");
            return;
        }

        double[][] new_adj = new double[n - 1][n - 1];
        String[] newNomes = new String[n - 1];
        double[] newPesos = new double[n - 1];

        int ni = 0;
        int nj = 0;
        int nk = 0;
        int count = degree(v);

        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj[i].length; ++j) {
                if (i == v) {
                    ni--;
                    break;
                }

                else if (j == v) {
                    continue;
                }

                else {
                    new_adj[ni][nj] = adj[i][j];
                    nj++;
                }
            }

            ni++;
            nj = 0;

            if (i != v) {
                newNomes[nk] = nomes[i];
                newPesos[nk] = pesos[i];
                nk++;
            }
        }

        n--;
        m -= count;
        pesos[v] = Double.POSITIVE_INFINITY;
        this.nomes = newNomes;
        this.adj = new_adj;
        this.pesos = newPesos;
    }

    public int isCompleto() {
        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                if (i != j && adj[i][j] == Double.POSITIVE_INFINITY) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public double[][] complemento() {
        double[][] complemen = new double[n][n];
        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                if (i != j && adj[i][j] != Double.POSITIVE_INFINITY) {
                    complemen[i][j] = Double.POSITIVE_INFINITY;
                }

                else if (i != j && adj[i][j] == Double.POSITIVE_INFINITY) {
                    complemen[i][j] = -1;
                }
            }
        }

        return complemen;
    }

    public int conectividadade() {
        int[] vet = new int[adj.length];
        conectividadeAux(vet, 0);

        for (int i = 0; i < adj.length; ++i) {
            if (vet[i] == 0) {
                return 1;
            }
        }

        return 0;
    }

    public void conectividadeAux(int[] vet, int v) {
        if (vet[v] == 0) {
            vet[v] = 1;
        }
        int[] fechosTransitivos = geraFechos(v, adj);
        int[] fechosIntransitivos = geraFechos(v, inverte());

        for (int j = 0; j < adj.length; ++j) {
            if ((fechosTransitivos[j] == 1 || fechosIntransitivos[j] == 1) && vet[j] == 0) {
                conectividadeAux(vet, j);
            }
        }
    }

    private int[] geraFechos(int n, double[][] matriz) {
        int[] fechos = new int[matriz.length];
        geraFechosAux(fechos, n, matriz);
        return fechos;
    }

    private void geraFechosAux(int[] fechos, int v, double[][] matriz) {
        if (fechos[v] == 0) {
            fechos[v] = 1;
        }

        for (int i = 0; i < fechos.length; ++i) {
            if (matriz[v][i] != Double.POSITIVE_INFINITY && fechos[i] == 0) {
                geraFechosAux(fechos, i, matriz);
            }
        }
    }

    private double[][] inverte() {
        double[][] inverso = new double[adj.length][adj.length];
        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                inverso[j][i] = adj[i][j];
            }
        }

        return inverso;
    }

    public int grauConexidade() {
        if (grauTres() == 0) {
            return 3;
        } else if (grauDois() == 0) {
            return 2;
        } else if (conectividadade() == 0) {
            return 1;
        } else if (conectividadade() == 1) {
            return 0;
        }
        return -1;
    }

    private int grauTres() {
        for (int i = 0; i < adj.length; ++i) {
            int[] fechosTransitivos = geraFechos(i, adj);
            int[] fechosIntransitivos = geraFechos(i, inverte());
            for (int j = 0; j < adj.length; ++j) {
                if (fechosTransitivos[j] == 0 || fechosIntransitivos[j] == 0) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private int grauDois() {
        for (int i = 0; i < adj.length; ++i) {
            int[] fechosTransitivos = geraFechos(i, adj);
            int[] fechosIntransitivos = geraFechos(i, inverte());
            for (int j = 0; j < adj.length; ++j) {
                if (fechosTransitivos[j] == 0 && fechosIntransitivos[j] == 0) {
                    return 1;
                }
            }
        }

        return 0;
    }

    int max(int[] vet) {
        int n = vet[0];
        for (int i = 1; i < vet.length; ++i) {
            if (vet[i] > n) {
                n = vet[i];
            }
        }

        return n;
    }

    public TGrafoPesoRotulado grafoReduzido() {
        int[] vet = new int[adj.length];
        int count = 1;
        for (int i = 0; i < adj.length; ++i) {
            int[] direto = geraFechos(i, adj);
            int[] indireto = geraFechos(i, inverte());
            for (int j = 0; j < adj.length; ++j) {
                if (direto[j] == 1 && indireto[j] == 1 && vet[j] == 0) {
                    vet[j] = count;
                }
            }

            count = max(vet) + 1;
        }

        TGrafoPesoRotulado grafo = new TGrafoPesoRotulado(max(vet));

        for (int i = 0; i < adj.length; ++i) {
            int[] direto = geraFechos(i, adj);
            int[] indireto = geraFechos(i, inverte());
            for (int j = 0; j < adj.length; ++j) {
                if (direto[j] == 1 && indireto[j] == 0 && adj[i][j] != Double.POSITIVE_INFINITY) {
                    grafo.insereA(vet[i] - 1, vet[j] - 1, -1);
                }

                else if (direto[j] == 0 && indireto[j] == 1 && adj[j][i] != Double.POSITIVE_INFINITY) {
                    grafo.insereA(vet[j] - 1, vet[i] - 1, -1);
                }
            }
        }

        return grafo;
    }

    public void profundidade() {
        Pilha pilha = new Pilha();
        int[] vet = new int[adj.length];
        int n = 0;
        System.out.print("\nPercurso em profundidade: " + n);
        vet[n] = 1;
        pilha.push(n);
        while (!pilha.isEmpty()) {
            n = pilha.pop();
            for (int m = 0; m < adj.length; ++m) {
                if (adj[n][m] != Double.POSITIVE_INFINITY && vet[m] == 0) {
                    System.out.print(" " + m);
                    vet[m] = 1;
                    pilha.push(n);
                    n = m;
                }
            }
        }
    }

    public void largura() {
        FilaCircular fila = new FilaCircular();
        int[] vet = new int[adj.length];
        int n = 0;
        System.out.print("\nPercurso em largura: " + n);
        vet[n] = 1;
        fila.enqueue(n);
        while (!fila.qIsEmpty()) {
            n = fila.dequeue();
            for (int m = 0; m < adj.length; ++m) {
                if (adj[n][m] != Double.POSITIVE_INFINITY && vet[m] == 0) {
                    System.out.print(" " + m);
                    vet[m] = 1;
                    fila.enqueue(m);
                }
            }
        }
    }

    public void insereV(int peso, String nome) {
        int newN = n + 1;
        pesos[n] = peso;
        double[][] newAdj = new double[newN][newN];
        String[] newNomes = new String[newN];
        double[] newPesos = new double[newN];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                newAdj[i][j] = adj[i][j];
                newAdj[newN - 1][i] = Double.POSITIVE_INFINITY;
                newAdj[i][newN - 1] = Double.POSITIVE_INFINITY;
            }

            newNomes[i] = nomes[i];
            newPesos[i] = pesos[i];
        }

        newAdj[newN - 1][newN - 1] = Double.POSITIVE_INFINITY;
        newNomes[n] = nome;
        newPesos[n] = peso;
        this.n = newN;
        this.adj = newAdj;
        this.nomes = newNomes;
        this.pesos = newPesos;
    }
}
