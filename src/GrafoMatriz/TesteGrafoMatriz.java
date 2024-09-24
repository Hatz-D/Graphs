/*
Diogo Lourenzon Hatz     - 10402406
Nicolas Fernandes Melnik - 10402170

Classe: Main do programa, contém lógica do menu, seleção de opções e filtro de entrada

Mudanças:
- Versão inicial da main da aplicação, como menu de opções - Diogo Hatz, 10/09/2024
- Separação das classes de grafo em classes distintas e criação de classe abstrata Grafos- Diogo Hatz, 11/09/2024
- Criação da classe Projeto para métodos de manipulação de arquivos - Diogo Hatz, 11/09/2024
- Garantir consistência dos métodos das classes dos grafos - Nicolas Melnik 13/09/2024
*/

package GrafoMatriz;
import java.io.IOException;
import java.util.Scanner;

public class TesteGrafoMatriz {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Scanner text = new Scanner(System.in);
		Grafo grafo = null;
		String menu = "\n---------------------------------------------------------" +
				"\nSeja bem-vindo ao seu melhor roteiro de viagens!\n\n" +
				"1. Ler dados do arquivo\n" +
				"2. Gravar dados no arquivo\n" +
				"3. Inserir vértice\n" +
				"4. Inserir aresta\n" +
				"5. Remove vértice\n" +
				"6. Remove aresta\n" +
				"7. Mostrar conteúdo do arquivo\n" +
				"8. Mostrar grafo\n" +
				"9. Apresentar a conexidade do grafo\n" +
				"10. Encerrar a aplicação\n";

		int opcao = -1;

		while(opcao != 10) {
			System.out.print(menu + "\nInsira uma opção: ");
			try{
				opcao = Integer.parseInt(scanner.nextLine());
			}
			catch (Exception e) {
				opcao = -1;
			}

			switch (opcao) {
				case 1 -> {
					grafo = Projeto.lerArquivo("grafo.txt");
					grafo.show();
				}

				case 2 -> {
					if(grafo == null) {
						System.out.println("\nAdicione um grafo com a opção 1!");
						break;
					}

					String file = "arquiv.txt";
					Projeto.gravarArquivo(grafo, file);
				}

				case 3 -> {
					if(grafo == null) {
						System.out.println("\nAdicione um grafo com a opção 1!");
						break;
					}

					if(grafo instanceof TGrafoPeso || grafo instanceof TGrafoPesoRotulado || grafo instanceof TGrafoNDPeso || grafo instanceof TGrafoNDPesoRotulado) {
						System.out.print("Insira o peso do novo vértice e o seu nome no formato: peso nome: ");
						try {
							String[] vet = text.nextLine().strip().split(" ");
							grafo.insereV(Double.parseDouble(vet[0]), vet[1]);
						}

						catch (Exception e) {
							System.out.println("\nSomente insira números no peso!");
							break;
						}
					}

					else {
						System.out.print("Insira o nome do vértice: ");
						grafo.insereV(text.nextLine());
					}

					System.out.println("\nVértice inserido com sucesso!");
					grafo.show();
				}

				case 4 -> {
					if(grafo == null) {
						System.out.println("\nAdicione um grafo com a opção 1!");
						break;
					}

					if (grafo instanceof TGrafoRotulado || grafo instanceof TGrafoPesoRotulado || grafo instanceof TGrafoNDRotulado || grafo instanceof TGrafoNDPesoRotulado) {
						System.out.print("\nInsira a aresta no formato: v1 v2 peso: ");

						try {
							String[] line = text.nextLine().split(" ");
							int v1 = Integer.parseInt(line[0]);
							int v2 = Integer.parseInt(line[1]);
							double peso = Double.parseDouble(line[2]);

							grafo.insereA(v1, v2, peso);
						}

						catch(Exception e) {
							System.out.println("\nFormato incorreto inserido!");
							break;
						}
					}

					else {
						System.out.print("\nInsira a aresta no formato: v1 v2: ");

						try {
							String[] line = text.nextLine().split(" ");
							int v1 = Integer.parseInt(line[0]);
							int v2 = Integer.parseInt(line[1]);

							grafo.insereA(v1, v2);
						}

						catch (Exception e) {
							System.out.println("\nFormato incorreto inserido!");
							break;
						}
					}

					System.out.println("Aresta inserida com sucesso!");
					grafo.show();
				}

				case 5 -> {
					if(grafo == null) {
						System.out.println("\nAdicione um grafo com a opção 1!");
						break;
					}

					System.out.print("Insira o vértice a ser removido: ");

					try {
						int v = Integer.parseInt(text.nextLine());
						grafo.removeV(v);
						System.out.println("\nVértice removido com sucesso!");
					}

					catch(Exception e) {System.out.println("\nFormato incorreto inserido!");}
				}

				case 6 -> {
					if(grafo == null) {
						System.out.println("\nAdicione um grafo com a opção 1!");
						break;
					}

					System.out.print("\nInsira a aresta no formato: v1 v2: ");

					try{
						String[] line = text.nextLine().split(" ");
						int v1 = Integer.parseInt(line[0]);
						int v2 = Integer.parseInt(line[1]);

						grafo.removeA(v1, v2);
						System.out.println("Aresta removida com sucesso!");
						grafo.show();
					}
					catch(Exception e){System.out.println("\nFormato incorreto inserido!");}
				}

				case 7 -> {
					Grafo aux;
					aux = Projeto.lerArquivo("grafo.txt");
					aux.show();

					if(aux instanceof TGrafoND) {
						System.out.println("\nGrafo não orientado sem peso");
					}

					else if(aux instanceof TGrafoNDPeso) {
						System.out.println("\nGrafo não orientado com peso no vértice");
					}

					else if(aux instanceof TGrafoNDRotulado) {
						System.out.println("\nGrafo não orientado com peso na aresta");
					}

					else if(aux instanceof TGrafoNDPesoRotulado) {
						System.out.println("\nGrafo não orientado com peso no vértice e na aresta");
					}

					else if(aux instanceof TGrafo) {
						System.out.println("\nGrafo orientado sem peso");
					}

					else if(aux instanceof TGrafoPeso) {
						System.out.println("\nGrafo orientado com peso no vértice");
					}

					else if(aux instanceof TGrafoRotulado) {
						System.out.println("\nGrafo orientado com peso na aresta");
					}

					else if(aux instanceof TGrafoPesoRotulado) {
						System.out.println("\nGrafo orientado com peso no vértice e na aresta");
					}
				}

				case 8 -> {
					if(grafo == null) {
						System.out.println("\nAdicione um grafo com a opção 1!");
						break;
					}

					System.out.println("Imprimindo o grafo...\n");
					grafo.show();
					System.out.println("\nClasse: " + grafo.getClass().getName());
				}

				case 9 -> {
					if(grafo == null) {
						System.out.println("\nAdicione um grafo com a opção 1!");
						break;
					}

					if(grafo instanceof TGrafoND || grafo instanceof TGrafoNDPeso || grafo instanceof TGrafoNDPesoRotulado || grafo instanceof TGrafoNDRotulado) {
						System.out.println("\nConexidade do grafo: " + grafo.conectividadade() + (grafo.conectividadade() == 0 ? " - Conexo" : " - Não Conexo"));
					}

					else {
						int n = -1;

						if(grafo instanceof TGrafo) {
							n = ((TGrafo)grafo).grauConexidade();
							((TGrafo)grafo).grafoReduzido().show();
						}

						else if(grafo instanceof TGrafoPeso) {
							n = ((TGrafoPeso)grafo).grauConexidade();
							((TGrafoPeso)grafo).grafoReduzido().show();
						}

						else if(grafo instanceof TGrafoRotulado) {
							n = ((TGrafoRotulado)grafo).grauConexidade();
							((TGrafoRotulado)grafo).grafoReduzido().show();
						}

						else if(grafo instanceof TGrafoPesoRotulado) {
							n = ((TGrafoPesoRotulado)grafo).grauConexidade();
							((TGrafoPesoRotulado)grafo).grafoReduzido().show();
						}

						System.out.print("\nConexidade do grafo: ");

						switch(n) {
							case 0 -> System.out.print(" C0 - Não Conexo");

							case 1 -> System.out.print(" C1 - Simplesmente Conexo");

							case 2 -> System.out.print(" C2 - Semi-Fortemente Conexo");

							case 3 -> System.out.print(" C3 - Fortemente Conexo");
						}
					}
				}

				case 10 -> {}

				default -> System.out.println("\nOpção inválida inserida!");
			}
		}
	}
}
