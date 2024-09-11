package GrafoMatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo extends Grafo {
	// Atributos Privados
	private int[][] adj; //matriz de adjacência

	// Métodos Públicos
	public TGrafo(int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new int [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=0;	
	}

	public TGrafo(int[][] adj) {
		this.adj = adj;
		this.n = adj.length;
		int count = 0;

		for(int i = 0; i < adj.length; ++i) {
			for(int j = 0; j < adj[i].length; j++) {
				if(adj[i][j] != 0) {count++;}
			}
		}

		this.m = count;
	}

	public int[][] getAdj() {return adj;}

	// Insere uma aresta no Grafo tal que
	// v é adjacente a w
	public void insereA(int v, int w) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == 0) {
	        adj[v][w] = 1;
	        m++; // atualiza qtd arestas
	    }

		//else{System.out.println("\nAresta já existente!");}
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] == 1 ){
	        adj[v][w] = 0;
	        m--; // atualiza qtd arestas
	    }

		else{System.out.println("\nAresta não existente!");}
	}
	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show() {
	    System.out.println("V: " + n );
	    System.out.println("A: " + m );
	    for(int i=0; i < n; i++){
	    	System.out.print("\n");
	        for(int w=0; w < n; w++)
	            if(adj[i][w] == 1)
	            	System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo.\n\nImpressão dos nomes:" );

		for(int i = 0; i < nomes.length; ++i) {
			System.out.print(i + ": " + nomes[i] + ", ");
		}
	}

	public int inDegree(int v) {
		int count = 0;
		for(int i = 0; i < adj.length; ++i) {
			if(adj[i][v] != 0) {
				count++;
			}
		}

		return count;
	}

	public int outDegree(int v) {
		int count = 0;
		for(int i = 0; i < adj[v].length; ++i) {
			if(adj[v][i] != 0) {
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
		for(int i = 0; i < adj.length; ++i) {
			for(int j = i + 1; j < adj[i].length; j++) {
				if(adj[i][j] != adj[j][i]) {return 0;}
			}
		}

		return 1;
	}

	public int isSimetrico(int[][] adj) {
		for(int i = 0; i < adj.length; ++i) {
			for(int j = i + 1; j < adj[i].length; j++) {
				if(adj[i][j] != adj[j][i]) {return 0;}
			}
		}

		return 1;
	}

	public static int[][] arquivo(String nome_arquivo) {
		File file = new File(nome_arquivo);
		Scanner scanner;

		try{scanner = new Scanner(file);}

		catch(FileNotFoundException e) {
			System.out.println("Arquivo não encontado!");
			return null;
		}

		int v = Integer.parseInt(scanner.nextLine().strip());
		int a = Integer.parseInt(scanner.nextLine().strip());

		int[][] adj = new int[v][v];

		for(int i = 0; i < a; ++i) {
			String[] array = scanner.nextLine().strip().split(" ");
			int prim = Integer.parseInt(array[0]);
			int sec = Integer.parseInt(array[1]);

			adj[prim][sec] = 1;
		}

		scanner.close();
		return adj;
	}

	public void removeV(int v) {
		if(v > n-1) {
			System.out.println("Vértice nao existente!");
			return;
		}

		int[][] new_adj = new int[n-1][n-1];
		String[] newNomes = new String[n-1];
		int ni = 0;
		int nj = 0;
		int nk = 0;
		int count = degree(v);

		for(int i = 0; i < adj.length; ++i) {
			for(int j = 0; j < adj[i].length; ++j) {
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
			for(int j = 0; j < adj[0].length; ++j) {
				if(i != j && adj[i][j] != 1) {return 0;}
			}
		}

		return 1;
	}

	public int[][] complemento() {
		int[][] complemen = new int[n][n];
		for(int i = 0; i < adj.length; ++i) {
			for(int j = 0; j < adj[0].length; ++j) {
				if(i != j && adj[i][j] == 1) {complemen[i][j] = 0;}
				else if(i != j && adj[i][j] == 0) {complemen[i][j] = 1;}
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

	private void conectividadeAux(int[] vet, int v) {
		if (vet[v] == 0) {vet[v] = 1;}
		int[] fechosTransitivos = geraFechos(v, adj);
		int[] fechosIntransitivos = geraFechos(v, inverte());

		for (int j = 0; j < adj.length; ++j) {
			if((fechosTransitivos[j] == 1 || fechosIntransitivos[j] == 1) && vet[j] == 0) {
				conectividadeAux(vet, j);
			}
		}
	}

	private int[] geraFechos(int n, int[][] matriz) {
		int[] fechos = new int[matriz.length];
		geraFechosAux(fechos, n, matriz);
		return fechos;
	}

	private void geraFechosAux(int[] fechos, int v, int[][] matriz) {
		if(fechos[v] == 0) {fechos[v] = 1;}

		for(int i = 0; i < fechos.length; ++i) {
			if(matriz[v][i] != 0 && fechos[i] == 0) {geraFechosAux(fechos, i, matriz);}
		}
	}

	private int[][] inverte() {
		int[][] inverso = new int[adj.length][adj.length];
		for(int i = 0; i < adj.length; ++i) {
			for(int j = 0; j < adj.length; ++j) {
				inverso[j][i] = adj[i][j];
			}
		}

		return inverso;
	}

	public int grauConexidade() {
		if(grauTres() == 0) {return 3;}
		else if(grauDois() == 0) {return 2;}
		else if(conectividadade() == 0) {return 1;}
		else if(conectividadade() == 1) {return 0;}
		return -1;
	}

	private int grauTres() {
		for (int i = 0; i < adj.length; ++i) {
			int[] fechosTransitivos = geraFechos(i, adj);
			int[] fechosIntransitivos = geraFechos(i, inverte());
			for(int j = 0; j < adj.length; ++j) {
				if(fechosTransitivos[j] == 0 || fechosIntransitivos[j] == 0) {
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
			for(int j = 0; j < adj.length; ++j) {
				if(fechosTransitivos[j] == 0 && fechosIntransitivos[j] == 0) {
					return 1;
				}
			}
		}

		return 0;
	}

	int max(int[] vet) {
		int n = vet[0];
		for(int i = 1; i < vet.length; ++i) {
			if(vet[i] > n) {n = vet[i];}
		}

		return n;
	}

	public TGrafo grafoReduzido() {
		int[] vet = new int[adj.length];
		int count = 1;
		for(int i = 0; i < adj.length; ++i) {
			int[] direto = geraFechos(i, adj);
			int[] indireto = geraFechos(i, inverte());
			for(int j = 0; j < adj.length; ++j) {
				if(direto[j] == 1 && indireto[j] == 1 && vet[j] == 0) {
					vet[j] = count;
				}
			}

			count = max(vet) + 1;
		}

		TGrafo grafo = new TGrafo(max(vet));

		for(int i = 0; i < adj.length; ++i) {
			int[] direto = geraFechos(i, adj);
			int[] indireto = geraFechos(i, inverte());
			for (int j = 0; j < adj.length; ++j) {
				if(direto[j] == 1 && indireto[j] == 0 && adj[i][j] == 1) {
					grafo.insereA(vet[i] - 1, vet[j] - 1);
				}

				else if(direto[j] == 0 && indireto[j] == 1 && adj[j][i] == 1) {
					grafo.insereA(vet[j] - 1, vet[i] - 1);
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
		while(!pilha.isEmpty()) {
			n = pilha.pop();
			for(int m = 0; m < adj.length; ++m) {
				if(adj[n][m] == 1 && vet[m] == 0) {
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
				if(adj[n][m] == 1 && vet[m] == 0) {
					System.out.print(" " + m);
					vet[m] = 1;
					fila.enqueue(m);
				}
			}
		}
	}

	public void ordenacaoTopologica() {
		FilaCircular fila = new FilaCircular();
		int[] GE = new int[adj.length];
		int n;
		for (int i = 0; i < GE.length; ++i) {
			GE[i] = inDegree(i);
			if(GE[i] == 0) {
				fila.enqueue(i);
				GE[i] = -1;
			}
		}

		System.out.print("Ordenação Topológica:");

		while(!fila.qIsEmpty()) {
			n = fila.dequeue();
			System.out.print(" " + n);
			for(int i = 0; i < adj.length; i++) {
				if(adj[n][i] == 1) {
					GE[i]--;
				}
			}

			for(int i = 0; i < adj.length; i++) {
				if(GE[i] == 0) {
					fila.enqueue(i);
					GE[i] = -1;
				}
			}
		}

		System.out.println(" ");
	}

	public void insereV(String nome) {
		int newN = n + 1;
		int[][] newAdj = new int[newN][newN];
		String[] newNomes = new String[newN];

		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				newAdj[i][j] = adj[i][j];
				newAdj[newN-1][i] = 0;
				newAdj[i][newN-1] = 0;
			}

			newNomes[i] = nomes[i];
		}

		newAdj[newN-1][newN-1] = 0;
		newNomes[n] = nome;
		this.n = newN;
		this.adj = newAdj;
		this.nomes = newNomes;
	}
}
