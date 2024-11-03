#include <stdio.h>
#include <stdlib.h>

#define TAMANHO_PAGINA 256
#define NUM_FRAMES 4
#define NUM_PAGINAS 8
#define MAX_ENDERECOS 10

typedef struct {
    int pagina;
    int ocupado;
       // esta representando um frame da memória física, com atributos para armazenar o número da página e indicar se o frame está ocupado.
} Frame;

typedef struct {
    int numero_frame;
        // esta representando uma pagina virtual, com um atributo para armazenar o número do frame mapeado.
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
                 // fica iterando pelos frames para encontrar um livre, mapeia a pagina virtual para o frame e marca como ocupado. dai envia um erro se não houver frames livres.
        }
    }
    printf("Erro: Memória física cheia. Não é possível mapear a página %d.\n", numero_pagina);
    return -1;
}

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
        // calcula o número da pagina e o offset do endereço virtual, verifica se a página esta mapeada para calcular o endereço fisico ou envia um page fault caso não esteja.

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

int main() {
    MemoriaFisica memoria;
    inicializarMemoriaFisica(&memoria);

    Processo processo = {
        .id = 1,
        .enderecos_virtuais = {512, 768, 1024, 1280, 256},
        .num_enderecos = 5
    };
    inicializarTabelaPaginas(&processo.tabela_paginas);

    simularProcesso(&processo, &memoria);

    return 0;
}