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

|`1`|`2`|`3`|`4`|`2`|`1`|`5`|`6`|`2`|`1`|`2`|`3`|`7`|`6`|`3`|`2`|`1`|`2`|`3`|`6`|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| 🚫1 | 1 | 1 | 🚫4 | 4 | 4 | 4 | 🚫6 | 6 | 6 | 6 | 🚫3 | 3 | 3 | 3 | 🚫2 | 2 | 2 | 2 | 🚫6 |
| - | 🚫2 | 2 | 2 | 2 | 🚫1 | 1 | 1 | 🚫2 | 2 | 2 | 2 | 7 | 🚫7 | 7 | 7 | 🚫1 | 1 | 1 | 1 |
| - | - | 🚫3 | 3 | 3 | 3 | 🚫5 | 5 | 5 | 🚫1 | 1 | 1 | 1 | 6 | 🚫6 | 6 | 6 | 6 | 🚫3 | 3 |

**Total de Faltas de Página (FIFO): 16**

### LRU (Least Recently Used)

|`1`|`2`|`3`|`4`|`2`|`1`|`5`|`6`|`2`|`1`|`2`|`3`|`7`|`6`|`3`|`2`|`1`|`2`|`3`|`6`|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| 🚫1 | 1 | 1 | 🚫4 | 4 | 4 | 🚫5 | 5 | 5 | 🚫1 | 1 | 1 | 🚫7 | 7 | 7 | 🚫2 | 2 | 2 | 2 | 2 |
| - | 🚫2 | 2 | 2 | 2 | 2 | 2 | 🚫6 | 6 | 6 | 6 | 🚫3 | 3 | 3 | 3 | 3 | 3 | 3 | 3 | 3 |
| - | - | 🚫3 | 3 | 3 | 🚫1 | 1 | 1 | 🚫2 | 2 | 2 | 2 | 2 | 🚫6 | 6 | 6 | 🚫1 | 1 | 1 | 🚫6 |

**Total de Faltas de Página (LRU): 15**

### ÓTIMO

|`1`|`2`|`3`|`4`|`2`|`1`|`5`|`6`|`2`|`1`|`2`|`3`|`7`|`6`|`3`|`2`|`1`|`2`|`3`|`6`|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|🚫1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |🚫3 | 3 | 3 | 3 | 3 | 3 | 3 | 3 | 3 |
| - | 2 | 2 | 2 | 2 | 2 | 2 | 2 | 2 | 2 | 2 | 2 |🚫7 | 7 | 7 |🚫2 | 2 | 2 | 2 | 2 |
| - | - |🚫3 |🚫4 | 4 | 4 |🚫5 |🚫6 | 6 | 6 | 6 | 6 | 6 | 6 | 6 | 6 |🚫1 | 1 | 1 |🚫6 |

**Total de Faltas de Página (ÓTIMO): 11**

## Conclusão

A simulação dos algoritmos de substituição de página resultou nas seguintes faltas de página:

- FIFO: **16 faltas**
- LRU: **15 faltas**
- ÓTIMO: **11 faltas**
