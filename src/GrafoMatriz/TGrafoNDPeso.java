/*
Diogo Lourenzon Hatz     - RA: 10402406
Eduardo Marui de Camargo - RA: 10400734
Nicolas Fernandes Melnik - RA: 10402170

Classe: Classe relativa ao grafo direcionado com peso nas arestas

Mudanças:
- Versão inicial da main da aplicação, como menu de opções - Diogo Hatz, 10/09/2024
- Separação das classes de grafo em classes distintas e criação de classe abstrata Grafos- Diogo Hatz, 11/09/2024
- Criação da classe Projeto para métodos de manipulação de arquivos - Diogo Hatz, 11/09/2024
- Garantir consistência dos métodos das classes dos grafos - Nicolas Melnik 13/09/2024
- Adicionando mapeamento da europa no arquivo grafo.txt - Nicolas Melnik e Eduardo Marui 24/09/2024
- Adicionando relatório e README do projeto - Nicolas Melnik e Eduardo Marui 25/09/2024

- Adicionando função relativa ao roteiro de viagem e suas funções auxiliares - Diogo Hatz, 26/10/2024
- Modificando estrutura do arquivo 'grafo.txt' - Diogo Hatz, 26/10/2024
- Adicionando coloração ao código - Nicolas Melnik, 03/11/2024
- Adicionando busca em profundidade, busca em largura e caminho minimo de dijkstra - Diogo Hatz, 04/11/2024
- Arrumando uma aresta no arquivo grafo.txt - Nicolas Melnik 10/11/2024
- Correção do Título do projeto - Nicolas Melnik 10/11/2024
- Modificação das funções de percurso em largura e profundidade - Eduardo Marui 14/11/2024
*/

package GrafoMatriz;

public class TGrafoNDPeso extends Grafo {
    // Atributos Privados
    protected int[][] adj; // matriz de adjacência
    protected double[] pesos;

    // Métodos Públicos
    public TGrafoNDPeso(int n) { // construtor
        this.n = n;
        // No início dos tempos não há arestas
        this.m = 0;
        // alocação da matriz do TGrafo
        this.adj = new int[n][n];
        this.pesos = new double[n];
        this.nomes = new String[n];

        // Inicia a matriz com zeros
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.adj[i][j] = 0;
            }
            pesos[i] = Double.POSITIVE_INFINITY;
        }
    }

    public TGrafoNDPeso(int[][] adj, double[] pesos) {
        this.adj = adj;
        this.n = adj.length;
        this.pesos = pesos;
        this.nomes = new String[n];
        int count = 0;

        for (int i = 0; i < adj.length; ++i) {
            for (int j = i + 1; j < adj.length; j++) {
                if (adj[i][j] != 0) {
                    count++;
                }
            }
        }
        this.m = count;
    }

    public int[][] getAdj() {
        return adj;
    }

    public void setAdj(int[][] adj) {
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
    public void insereA(int v, int w) {
        // testa se nao temos a aresta
        if (adj[v][w] == 0 && adj[w][v] == 0) {
            adj[v][w] = 1;
            adj[w][v] = 1;
            ++m; // atualiza qtd arestas
        }

        // else{System.out.println("\nAresta já existente!");}
    }

    // remove uma aresta v->w do Grafo
    public void removeA(int v, int w) {
        // testa se temos a aresta
        if (adj[v][w] == 1 && adj[w][v] == 1) {
            adj[v][w] = 0;
            adj[w][v] = 0;
            --m; // atualiza qtd arestas
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
                if (adj[i][w] == 1)
                    System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
                else
                    System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
        }
        System.out.println("\n\nfim da impressao do grafo.\n\nImpressão dos nomes:");

        for (int i = 0; i < nomes.length; ++i) {
            System.out.print(i + ": " + nomes[i] + ", ");
        }

        System.out.println("Impressão dos pesos dos vértices:");

        for (int i = 0; i < n; ++i) {
            System.out.print(i + ": " + pesos[i] + ", ");
        }
    }

    public int degree(int v) {
        int count = 0;
        for (int i = 0; i < adj.length; ++i) {
            if (adj[i][v] != 0 && adj[v][i] != 0) {
                count++;
            }
        }

        return count;
    }

    public void removeV(int v) {
        if (v > n - 1) {
            System.out.println("Vértice nao existente!");
            return;
        }

        int[][] new_adj = new int[n - 1][n - 1];
        String[] newNomes = new String[n - 1];
        double[] newPesos = new double[n - 1];

        int ni = 0;
        int nj = 0;
        int nk = 0;
        int count = degree(v);

        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
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
        this.adj = new_adj;
        this.nomes = newNomes;
        this.pesos = newPesos;
    }

    public int isCompleto() {
        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                if (i != j && adj[i][j] != 1) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public int[][] complemento() {
        int[][] complemen = new int[n][n];
        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                if (i != j && adj[i][j] == 1) {
                    complemen[i][j] = 0;
                } else if (i != j && adj[i][j] == 0) {
                    complemen[i][j] = 1;
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
        int[] fechos = geraFechos(v);

        for (int j = 0; j < adj.length; ++j) {
            if (fechos[j] == 1 && vet[j] == 0) {
                conectividadeAux(vet, j);
            }
        }
    }

    private int[] geraFechos(int n) {
        int[] fechos = new int[adj.length];
        geraFechosAux(fechos, n);
        return fechos;
    }

    private void geraFechosAux(int[] fechos, int v) {
        if (fechos[v] == 0) {
            fechos[v] = 1;
        }

        for (int i = 0; i < fechos.length; ++i) {
            if (adj[v][i] != 0 && fechos[i] == 0) {
                geraFechosAux(fechos, i);
            }
        }
    }

    private int[][] inverte() {
        int[][] inverso = new int[adj.length][adj.length];
        for (int i = 0; i < adj.length; ++i) {
            for (int j = 0; j < adj.length; ++j) {
                inverso[j][i] = adj[i][j];
            }
        }

        return inverso;
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
                if (adj[n][m] == 1 && vet[m] == 0) {
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
                if (adj[n][m] == 1 && vet[m] == 0) {
                    System.out.print(" " + m);
                    vet[m] = 1;
                    fila.enqueue(m);
                }
            }
        }
    }

    public void insereV(int peso, String nome) {
        int newN = n + 1;
        int[][] newAdj = new int[newN][newN];
        String[] newNomes = new String[newN];
        double[] newPesos = new double[newN];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                newAdj[i][j] = adj[i][j];
                newAdj[newN - 1][i] = 0;
                newAdj[i][newN - 1] = 0;
            }

            newNomes[i] = nomes[i];
            newPesos[i] = pesos[i];
        }

        newAdj[newN - 1][newN - 1] = 0;
        newNomes[n] = nome;
        newPesos[n] = peso;
        this.n = newN;
        this.adj = newAdj;
        this.nomes = newNomes;
        this.pesos = newPesos;
    }
}
