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
*/

package GrafoMatriz;

import java.io.*;
import java.util.Scanner;

public class Projeto {
    public static Grafo lerArquivo(String file_path) {
        File file = new File(file_path);
        Grafo grafo = null;
        Scanner scanner;

        try {
            scanner = new Scanner(file);
        }

        catch (FileNotFoundException e) {
            System.out.println("\nArquivo não encontado!");
            return null;
        }

        int tipo = Integer.parseInt(scanner.nextLine().strip());
        int v = Integer.parseInt(scanner.nextLine().strip());

        String[] nomes = new String[v];

        switch (tipo) {
            case 0 -> {
                int[][] adj = new int[v][v];

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    if (array.length > 1) {
                        nomes[prim] = array[1];
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);

                    adj[prim][sec] = 1;
                    adj[sec][prim] = 1;
                }

                grafo = new TGrafoND(adj);
                grafo.setNomes(nomes);
            }

            case 1 -> {
                int[][] adj = new int[v][v];
                double[] pesos = new double[v];

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    nomes[prim] = array[1];
                    if (array.length > 2) {
                        pesos[i] = Double.parseDouble(array[2]);
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);

                    adj[prim][sec] = 1;
                    adj[sec][prim] = 1;
                }

                grafo = new TGrafoNDPeso(adj, pesos);
                grafo.setNomes(nomes);
            }

            case 2 -> {
                double[][] adj = new double[v][v];

                for (int i = 0; i < v; ++i) {
                    for (int j = 0; j < v; ++j) {
                        adj[i][j] = Double.POSITIVE_INFINITY;
                    }
                }

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    if (array.length > 1) {
                        nomes[prim] = array[1];
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);
                    double tri = Double.parseDouble(array[2]);

                    adj[prim][sec] = tri;
                    adj[sec][prim] = tri;
                }

                grafo = new TGrafoNDRotulado(adj);
                grafo.setNomes(nomes);
            }

            case 3 -> {
                double[][] adj = new double[v][v];
                double[] pesos = new double[v];

                for (int i = 0; i < v; ++i) {
                    for (int j = 0; j < v; ++j) {
                        adj[i][j] = Double.POSITIVE_INFINITY;
                    }
                }

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    nomes[prim] = array[1];
                    if (array.length > 2) {
                        pesos[i] = Double.parseDouble(array[2]);
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);
                    double tri = Double.parseDouble(array[2]);

                    adj[prim][sec] = tri;
                    adj[sec][prim] = tri;
                }

                grafo = new TGrafoNDPesoRotulado(adj, pesos);
                grafo.setNomes(nomes);
            }

            case 4 -> {
                int[][] adj = new int[v][v];

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    if (array.length > 1) {
                        nomes[prim] = array[1];
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);

                    adj[prim][sec] = 1;
                }

                grafo = new TGrafo(adj);
                grafo.setNomes(nomes);
            }

            case 5 -> {
                int[][] adj = new int[v][v];
                double[] pesos = new double[v];

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    nomes[prim] = array[1];
                    if (array.length > 2) {
                        pesos[i] = Double.parseDouble(array[2]);
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);

                    adj[prim][sec] = 1;
                }

                grafo = new TGrafoPeso(adj, pesos);
                grafo.setNomes(nomes);
            }

            case 6 -> {
                double[][] adj = new double[v][v];

                for (int i = 0; i < v; ++i) {
                    for (int j = 0; j < v; ++j) {
                        adj[i][j] = Double.POSITIVE_INFINITY;
                    }
                }

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    if (array.length > 1) {
                        nomes[prim] = array[1];
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);
                    double tri = Double.parseDouble(array[2]);

                    adj[prim][sec] = tri;
                }

                grafo = new TGrafoRotulado(adj);
                grafo.setNomes(nomes);
            }

            case 7 -> {
                double[][] adj = new double[v][v];
                double[] pesos = new double[v];

                for (int i = 0; i < v; ++i) {
                    for (int j = 0; j < v; ++j) {
                        adj[i][j] = Double.POSITIVE_INFINITY;
                    }
                }

                for (int i = 0; i < v; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    nomes[prim] = array[1];
                    if (array.length > 2) {
                        pesos[i] = Double.parseDouble(array[2]);
                    }
                }

                int a = Integer.parseInt(scanner.nextLine().strip());

                for (int i = 0; i < a; ++i) {
                    String[] array = scanner.nextLine().strip().split(" ");
                    int prim = Integer.parseInt(array[0]);
                    int sec = Integer.parseInt(array[1]);
                    double tri = Double.parseDouble(array[2]);

                    adj[prim][sec] = tri;
                }

                grafo = new TGrafoPesoRotulado(adj, pesos);
                grafo.setNomes(nomes);
            }

            default -> {
                System.out.println("\nTipo incorreto de grafo fornecido!");
                return null;
            }
        }

        scanner.close();
        return grafo;
    }

    public static void gravarArquivo(Grafo grafo, String file_path) throws IOException {
        BufferedWriter writter = new BufferedWriter(new FileWriter(file_path));

        if (grafo instanceof TGrafoND) {
            writter.write("0\n");
        }

        else if (grafo instanceof TGrafoNDPeso) {
            writter.write("1\n");
        }

        else if (grafo instanceof TGrafoNDRotulado) {
            writter.write("2\n");
        }

        else if (grafo instanceof TGrafoNDPesoRotulado) {
            writter.write("3\n");
        }

        else if (grafo instanceof TGrafo) {
            writter.write("4\n");
        }

        else if (grafo instanceof TGrafoPeso) {
            writter.write("5\n");
        }

        else if (grafo instanceof TGrafoRotulado) {
            writter.write("6\n");
        }

        else if (grafo instanceof TGrafoPesoRotulado) {
            writter.write("7\n");
        }

        int n = grafo.getN();
        int m = grafo.getM();
        String[] nomes = grafo.getNomes();
        writter.write(n + "\n");

        for (int i = 0; i < n; ++i) {
            writter.write(i + " " + nomes[i]);
            if (grafo instanceof TGrafoNDPeso) {
                double[] pesos = ((TGrafoNDPeso) grafo).getPesos();
                writter.write(" " + pesos[i] + "\n");
            }

            else if (grafo instanceof TGrafoNDPesoRotulado) {
                double[] pesos = ((TGrafoNDPesoRotulado) grafo).getPesos();
                writter.write(" " + pesos[i] + "\n");
            }

            else if (grafo instanceof TGrafoPeso) {
                double[] pesos = ((TGrafoPeso) grafo).getPesos();
                writter.write(" " + pesos[i] + "\n");
            }

            else if (grafo instanceof TGrafoPesoRotulado) {
                double[] pesos = ((TGrafoPesoRotulado) grafo).getPesos();
                writter.write(" " + pesos[i] + "\n");
            }

            else {
                writter.write("\n");
            }
        }

        writter.write(m + "\n");

        if (grafo instanceof TGrafoND) {
            int[][] adj = ((TGrafoND) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    if (adj[i][j] != 0) {
                        writter.write(i + " " + j + "\n");
                    }
                }
            }
        }

        else if (grafo instanceof TGrafoNDPeso) {
            int[][] adj = ((TGrafoNDPeso) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    if (adj[i][j] != 0) {
                        writter.write(i + " " + j + "\n");
                    }
                }
            }
        }

        else if (grafo instanceof TGrafoNDRotulado) {
            double[][] adj = ((TGrafoNDRotulado) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    if (adj[i][j] != Double.POSITIVE_INFINITY) {
                        writter.write(i + " " + j + " " + adj[i][j] + "\n");
                    }
                }
            }
        }

        else if (grafo instanceof TGrafoNDPesoRotulado) {
            double[][] adj = ((TGrafoNDPesoRotulado) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    if (adj[i][j] != Double.POSITIVE_INFINITY) {
                        writter.write(i + " " + j + " " + adj[i][j] + "\n");
                    }
                }
            }
        }

        else if (grafo instanceof TGrafo) {
            int[][] adj = ((TGrafo) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (adj[i][j] != 0) {
                        writter.write(i + " " + j + "\n");
                    }
                }
            }
        }

        else if (grafo instanceof TGrafoPeso) {
            int[][] adj = ((TGrafoPeso) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (adj[i][j] != 0) {
                        writter.write(i + " " + j + "\n");
                    }
                }
            }
        }

        else if (grafo instanceof TGrafoRotulado) {
            double[][] adj = ((TGrafoRotulado) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (adj[i][j] != Double.POSITIVE_INFINITY) {
                        writter.write(i + " " + j + " " + adj[i][j] + "\n");
                    }
                }
            }

        }

        else if (grafo instanceof TGrafoPesoRotulado) {
            double[][] adj = ((TGrafoPesoRotulado) grafo).getAdj();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (adj[i][j] != Double.POSITIVE_INFINITY) {
                        writter.write(i + " " + j + " " + adj[i][j] + "\n");
                    }
                }
            }
        }

        writter.close();
    }
}
