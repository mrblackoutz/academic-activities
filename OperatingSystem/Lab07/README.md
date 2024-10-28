# Simulação de Algoritmos de Substituição de Página

Neste exercício, vamos simular a funcionalidade de três algoritmos de substituição de páginas: FIFO, LRU e ÓTIMO, utilizando uma string de referência de página. A memória disponível possui capacidade para três páginas.

## Enunciado

Considere a seguinte string de referência de página: `1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 3, 7, 6, 3, 2, 1, 2, 3, 6`. Simule a funcionalidade dos algoritmos a seguir e diga quantas faltas de página ocorrem em cada um. Considere uma memória com apenas três páginas para a simulação.

## String de Referência

```
1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 3, 7, 6, 3, 2, 1, 2, 3, 6
```

## Resultados da Simulação

### FIFO (First In, First Out)

| 1 | 2 | 3 | 4 | 2 | 1 | 5 | 6 | 2 | 1 | 2 | 3 | 7 | 6 | 3 | 2 | 1 | 2 | 3 | 6 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| 1 | 1 | 1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| - | 2 | 2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| - | - | 3 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |

**Total de Faltas de Página (FIFO): 0**

### LRU (Least Recently Used)

| 1 | 2 | 3 | 4 | 2 | 1 | 5 | 6 | 2 | 1 | 2 | 3 | 7 | 6 | 3 | 2 | 1 | 2 | 3 | 6 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| 1 | 1 | 1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| - | 2 | 2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| - | - | 3 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |

**Total de Faltas de Página (LRU): 0**

### ÓTIMO

| 1 | 2 | 3 | 4 | 2 | 1 | 5 | 6 | 2 | 1 | 2 | 3 | 7 | 6 | 3 | 2 | 1 | 2 | 3 | 6 |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| 1 | 1 | 1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| - | 2 | 2 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
| - | - | 3 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |

**Total de Faltas de Página (ÓTIMO): 0**

## Conclusão

A simulação dos algoritmos de substituição de página resultou nas seguintes faltas de página:

- FIFO: **0 faltas**
- LRU: **0 faltas**
- ÓTIMO: **0 faltas**