package GrafoMatriz;

public class TGrafoNDRotulado extends Grafo {
    // Atributos Privados
    protected double[][] adj; //matriz de adjacência

    // Métodos Públicos
    public TGrafoNDRotulado(int n) {  // construtor
        this.n = n;
        // No início dos tempos não há arestas
        this.m = 0;
        // alocação da matriz do TGrafo
        this.adj = new double[n][n];
        // Inicia a matriz com infinitos
        for(int i = 0; i< n; i++)
            for(int j = 0; j< n; j++)
                this.adj[i][j] = Double.POSITIVE_INFINITY;
    }

    public TGrafoNDRotulado(double[][] adj) {
        this.adj = adj;
        this.n = adj.length;
        int count = 0;

        for(int i = 0; i < adj.length; ++i) {
            for(int j = i+1; j < adj.length; j++) {
                if(adj[i][j] != Double.POSITIVE_INFINITY && i != j) {count++;}
            }
        }

        this.m = count;
    }

    public double[][] getAdj() {return adj;}

    public void setAdj(double[][] adj) {
        this.adj = adj;
    }

    // Insere uma aresta no Grafo tal que
    // v é adjacente a w
    public void insereA(int v, int w, double p) {
        // testa se nao temos a aresta
        if(adj[v][w] == Double.POSITIVE_INFINITY && adj[w][v] == Double.POSITIVE_INFINITY) {
            adj[v][w] = p;
            adj[w][v] = p;
            ++m; // atualiza qtd arestas
        }

        //else{System.out.println("\nAresta já existente!");}
    }

    // remove uma aresta v->w do Grafo
    public void removeA(int v, int w) {
        // testa se temos a aresta
        if(adj[v][w] != Double.POSITIVE_INFINITY && adj[w][v] != Double.POSITIVE_INFINITY){
            adj[v][w] = Double.POSITIVE_INFINITY;
            adj[w][v] = Double.POSITIVE_INFINITY;
            --m; // atualiza qtd arestas
        }

        else {System.out.println("\nAresta não existente!");}
    }

    // Apresenta o Grafo contendo
    // número de vértices, arestas
    // e a matriz de adjacência obtida
    public void show() {
        System.out.println("V: " + n );
        System.out.println("A: " + m );
        for(int i = 0; i < n; i++){
            System.out.print("\n");
            for(int w = 0; w < n; w++)
                if(adj[i][w] != Double.POSITIVE_INFINITY)
                    System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
                else System.out.print("Adj[" + i + "," + w + "]= ∞" + " ");
        }
        System.out.println("\n\nfim da impressao do grafo.\n\nImpressão dos nomes:" );

        for(int i = 0; i < nomes.length; ++i) {
            System.out.print(i + ": " + nomes[i] + ", ");
        }
    }

    public int degree(int v) {
        int count = 0;
        for(int i = 0; i < adj.length; ++i) {
            if(adj[i][v] != Double.POSITIVE_INFINITY && adj[v][i] != Double.POSITIVE_INFINITY) {
                count++;
            }
        }

        return count;
    }

    public void removeV(int v) {
        if(v > n-1) {
            System.out.println("Vértice nao existente!");
            return;
        }

        double[][] new_adj = new double[n-1][n-1];
        String[] newNomes = new String[n-1];
        int ni = 0;
        int nj = 0;
        int nk = 0;
        int count = degree(v);

        for(int i = 0; i < adj.length; ++i) {
            for(int j = 0; j < adj.length; ++j) {
                if(i == v) {
                    ni--;
                    break;
                }

                else if(j == v) {continue;}

                else{
                    new_adj[ni][nj] = adj[i][j];
                    nj++;
                }
            }

            ni++;
            nj = 0;

            if(i != v) {
                newNomes[nk] = nomes[i];
                nk++;
            }
        }

        n--;
        m -= count;
        this.adj = new_adj;
        this.nomes = newNomes;
    }

    public int isCompleto() {
        for(int i = 0; i < adj.length; ++i) {
            for(int j = 0; j < adj.length; ++j) {
                if(i != j && adj[i][j] == Double.POSITIVE_INFINITY) {return 0;}
            }
        }

        return 1;
    }

    public double[][] complemento() {
        double[][] complemen = new double[n][n];
        for(int i = 0; i < adj.length; ++i) {
            for(int j = 0; j < adj.length; ++j) {
                if(i != j && adj[i][j] != Double.POSITIVE_INFINITY) {
                    complemen[i][j] = Double.POSITIVE_INFINITY;
                }

                else if(i != j && adj[i][j] == Double.POSITIVE_INFINITY) {complemen[i][j] = -1;}
            }
        }

        return complemen;
    }

    public int conectividadade() {
        int[] vet = new int[adj.length];
        conectividadeAux(vet, 0);

        for(int i = 0; i < adj.length; ++i) {
            if(vet[i] == 0) {return 1;}
        }

        return 0;
    }

    public void conectividadeAux(int[] vet, int v) {
        if(vet[v] == 0) {vet[v] = 1;}
        int[] fechos = geraFechos(v);

        for(int j = 0; j < adj.length; ++j) {
            if(fechos[j] == 1 && vet[j] == 0) {
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
        if(fechos[v] == 0) {fechos[v] = 1;}

        for(int i = 0; i < fechos.length; ++i) {
            if(adj[v][i] != Double.POSITIVE_INFINITY && fechos[i] == 0) {geraFechosAux(fechos, i);}
        }
    }

    private double[][] inverte() {
        double[][] inverso = new double[adj.length][adj.length];
        for(int i = 0; i < adj.length; ++i) {
            for(int j = 0; j < adj.length; ++j) {
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
        while(!pilha.isEmpty()) {
            n = pilha.pop();
            for(int m = 0; m < adj.length; ++m) {
                if(adj[n][m] != Double.POSITIVE_INFINITY && vet[m] == 0) {
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
        while(!fila.qIsEmpty()) {
            n = fila.dequeue();
            for(int m = 0; m < adj.length; ++m) {
                if(adj[n][m] != Double.POSITIVE_INFINITY && vet[m] == 0) {
                    System.out.print(" " + m);
                    vet[m] = 1;
                    fila.enqueue(m);
                }
            }
        }
    }

    public void insereV(String nome) {
        int newN = n + 1;
        double[][] newAdj = new double[newN][newN];
        String[] newNomes = new String[newN];

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                newAdj[i][j] = adj[i][j];
                newAdj[newN-1][i] = Double.POSITIVE_INFINITY;
                newAdj[i][newN-1] = Double.POSITIVE_INFINITY;
            }

            newNomes[i] = nomes[i];
        }

        newAdj[newN-1][newN-1] = Double.POSITIVE_INFINITY;
        newNomes[n] = nome;
        this.n = newN;
        this.adj = newAdj;
        this.nomes = newNomes;
    }
}
