| Nome do Aluno | RA | Turma |
|---------------|----|----|
| Felipe Ujvari Gasparino de Sousa | 10418415 | 04p11 Noturno |
| Gustavo Nascimento Siqueira | 10419057 | 04p11 Noturno |

## Sumário
1. [Projete e implemente estruturas de dados para representar a memória física, virtual e a tabela de páginas.](#1-projete-e-implemente-estruturas-de-dados-para-representar-a-memória-física-virtual-e-a-tabela-de-páginas)
2. [Crie funções para inicializar essas estruturas e manipulá-las.](#2-crie-funções-para-inicializar-essas-estruturas-e-manipulá-las)
3. [Implemente a função de tradução de endereço virtual para físico.](#3-implemente-a-função-de-tradução-de-endereço-virtual-para-físico)
4. [Desenvolva um programa de teste que demonstre o funcionamento básico do seu simulador](#4-desenvolva-um-programa-de-teste-que-demonstre-o-funcionamento-básico-do-seu-simulador)

### 1. Projete e implemente estruturas de dados para representar a memória física, virtual e a tabela de páginas.

```c
typedef struct {
    int pagina;
    int ocupado;
} Frame;

typedef struct {
    int numero_frame;
} Pagina;

typedef struct {
    Pagina paginas[NUM_PAGINAS];
} TabelaPaginas;

typedef struct {
    Frame frames[NUM_FRAMES];
} MemoriaFisica;

typedef struct {
    int id;
    int enderecos_virtuais[MAX_ENDERECOS];
    int num_enderecos;
    TabelaPaginas tabela_paginas;
} Processo;
```

### 2. Crie funções para inicializar essas estruturas e manipulá-las.

```c
void inicializarMemoriaFisica(MemoriaFisica *memoria) {
    for (int i = 0; i < NUM_FRAMES; i++) {
        memoria->frames[i].pagina = -1;
        memoria->frames[i].ocupado = 0;
    }
    printf("Memória física inicializada com %d frames.\n", NUM_FRAMES);
}

void inicializarTabelaPaginas(TabelaPaginas *tabela) {
    for (int i = 0; i < NUM_PAGINAS; i++) {
        tabela->paginas[i].numero_frame = -1;
    }
    printf("Tabela de páginas inicializada com %d páginas.\n", NUM_PAGINAS);
}

int mapearPagina(TabelaPaginas *tabela, MemoriaFisica *memoria, int numero_pagina) {
    for (int i = 0; i < NUM_FRAMES; i++) {
        if (memoria->frames[i].ocupado == 0) {
            memoria->frames[i].pagina = numero_pagina;
            memoria->frames[i].ocupado = 1;
            tabela->paginas[numero_pagina].numero_frame = i;
            printf("Página %d mapeada para frame %d\n", numero_pagina, i);
            return i;
        }
    }
    printf("Erro: Memória física cheia. Não é possível mapear a página %d.\n", numero_pagina);
    return -1;
}
```

### 3. Implemente a função de tradução de endereço virtual para físico.

```c
int traduzirEndereco(TabelaPaginas *tabela, int endereco_virtual) {
    int numero_pagina = endereco_virtual / TAMANHO_PAGINA;
    int offset = endereco_virtual % TAMANHO_PAGINA;

    if (numero_pagina >= NUM_PAGINAS) {
        printf("Erro: Endereço virtual fora do limite.\n");
        return -1;
    }

    int numero_frame = tabela->paginas[numero_pagina].numero_frame;

    if (numero_frame == -1) {
        printf("Page Fault: Página %d não está presente na memória física.\n", numero_pagina);
        return -1;
    }

    int endereco_fisico = (numero_frame * TAMANHO_PAGINA) + offset;
    return endereco_fisico;
}

void simularProcesso(Processo *processo, MemoriaFisica *memoria) {
    printf("\nSimulando processo %d\n", processo->id);
    for (int i = 0; i < processo->num_enderecos; i++) {
        int endereco_virtual = processo->enderecos_virtuais[i];
        int numero_pagina = endereco_virtual / TAMANHO_PAGINA;

        printf("Tentando acessar endereço virtual %d (página %d)\n", endereco_virtual, numero_pagina);

        if (processo->tabela_paginas.paginas[numero_pagina].numero_frame == -1) {
            printf("Page Fault detectado para a página %d. Realizando mapeamento.\n", numero_pagina);
            mapearPagina(&processo->tabela_paginas, memoria, numero_pagina);
        }

        int endereco_fisico = traduzirEndereco(&processo->tabela_paginas, endereco_virtual);
        if (endereco_fisico != -1) {
            printf("Endereço virtual %d traduzido para endereço físico %d\n", endereco_virtual, endereco_fisico);
        }
        printf("\n");
    }
}
```

### 4. Desenvolva um programa de teste que demonstre o funcionamento básico do seu simulador

```c
int main() {
    MemoriaFisica memoria;
    inicializarMemoriaFisica(&memoria);

    Processo processo = {
        .id = 1,
        .enderecos_virtuais = {512, 768, 1024, 1280, 256},
        .num_enderecos = 6
    };
    inicializarTabelaPaginas(&processo.tabela_paginas);

    simularProcesso(&processo, &memoria);

    return 0;
}
```

## [CÓDIGO FONTE](https://github.com/mrblackoutz/academic-activities/blob/main/OperatingSystem/Lab08/main.c)