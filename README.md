# Mapa das Capitais Europeias: Distâncias por Rotas de Carro

## Integrantes do Grupo
- Diogo Lourenzon Hatz
- Eduardo Marui de Camargo
- Nicolas Fernandes Melnik

## Visão Geral do Projeto

O grafo gerado é direcionado, pois as estradas nem sempre possuem dois sentidos de tráfego, e fatores como o relevo podem resultar em trajetos assimétricos. Por exemplo, em áreas montanhosas, o caminho de ida pode ser mais longo que o de volta.

Este projeto consiste em modelar um grafo direcionado que mapeia as capitais dos países do continente europeu, levando em consideração as distâncias entre elas em viagens de carro. As distâncias foram obtidas utilizando o Google Maps para calcular as rotas rodoviárias.

## Considerações Especiais

- **Malta** e **Islândia**: Como são ilhas, suas rotas incluem viagens de balsa, sendo que a Islândia não possui rota de balsa viável para a Europa continental.
- **Reino Unido** e **França**: A travessia do Canal da Mancha foi incluída nas rotas.
- **Kaliningrado**: Considerado para fronteiras diretas com a Rússia.
- **Rússia** e **Cazaquistão**: Considerados como parte da Europa, apesar de também possuírem territórios na Ásia.
- **Rússia** e **Ucrânia**: Devido à guerra, o Google acaba sugerindo caminhos não convencionais entre esses países.

## Objetivos de Desenvolvimento Sustentável (ODS)

Este projeto contribui para os seguintes Objetivos de Desenvolvimento Sustentável da ONU:

- **ODS 9: Indústria, Inovação e Infraestrutura**  
  Ao desenvolver uma ferramenta para planejar rotas rodoviárias entre as capitais europeias, o projeto facilita o planejamento de viagens e melhora o uso da infraestrutura rodoviária existente.
  
- **ODS 11: Cidades e Comunidades Sustentáveis**  
  Ao otimizar rotas, o projeto ajuda a reduzir o consumo de combustível, o que resulta em menos emissões de gases de efeito estufa, contribuindo para cidades mais sustentáveis.

## Estrutura do Projeto

O projeto está dividido em:

- **src/GrafoMatriz**: Códigos fontes.
- **Grafo em .txt**: Arquivo .txt que possuí o grafo mapeado no formato que a aplicalção entende para executar..
- **Relatório**: Documento PDF contendo a documentação do projeto.
  
Acesse o grafo [Clicando aqui](http://graphonline.ru/pt/?graph=QXMdBIsixbPdtwdN).
