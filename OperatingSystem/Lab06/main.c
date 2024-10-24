#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

struct reg
{
    int conteudo;
    struct reg *prox;
};
typedef struct reg celula;

// Função para criar uma nova célula
celula *criar_celula(int valor)
{
    celula *nova = (celula *)malloc(sizeof(celula));
    if (nova == NULL)
    {
        printf("Erro: Falha na alocação de memória\n");
        exit(1);
    }
    nova->conteudo = valor;
    nova->prox = NULL;
    return nova;
}

// Função para imprimir todos os valores da lista
void imprimir_lista(celula *inicio)
{
    celula *atual = inicio;
    while (atual != NULL)
    {
        printf("%d ", atual->conteudo);
        atual = atual->prox;
    }
    printf("\n");
}

// Função para remover elementos da lista e liberar memória
void remover_lista(celula **inicio)
{
    celula *atual = *inicio;
    celula *proximo;
    while (atual != NULL)
    {
        proximo = atual->prox;
        free(atual);
        atual = proximo;
    }
    *inicio = NULL;
}

int main()
{
    setlocale(LC_ALL, "pt_BR.UTF-8");

    // Criando três instâncias do objeto célula
    celula *inicio = criar_celula(10);
    inicio->prox = criar_celula(20);
    inicio->prox->prox = criar_celula(30);

    printf("Lista criada: ");
    imprimir_lista(inicio);

    // Calculando a quantidade de memória gasta por cada instância da célula
    printf("Memória gasta por cada célula: %lu bytes\n", sizeof(celula));

    // Removendo elementos da lista e liberando memória
    remover_lista(&inicio);
    printf("Lista após remoção: ");
    imprimir_lista(inicio);

    // Calculando o máximo de elementos possíveis na fila
    unsigned long long memoria_disponivel = 16ULL * 1024 * 1024 * 1024; // Tenho 16 GB de RAM
    unsigned long long max_elementos = memoria_disponivel / sizeof(celula);
    printf("Máximo de elementos possíveis na fila: %llu\n", max_elementos);

    return 0;
}