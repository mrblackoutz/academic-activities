import java.util.LinkedList;
import java.util.Random;

public class HashDivLinkedList {
    private LinkedList<Integer>[] tabela;
    private int tamanho;
    private int colisoes;
    private int insercoes;
    private int maiorLista;

    @SuppressWarnings("unchecked")
    public HashDivLinkedList(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new LinkedList[tamanho];
        this.colisoes = 0;
        this.insercoes = 0;
        this.maiorLista = 0;
        
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int funcaoHash(int chave) {
        return chave % tamanho;
    }

    public void inserir(int chave) {
        int indice = funcaoHash(chave);
        if (!tabela[indice].isEmpty()) {
            colisoes++;
        }
        tabela[indice].add(chave);
        insercoes++;
        maiorLista = Math.max(maiorLista, tabela[indice].size());
    }

    public void realizarEnsaio(int quantidadeValores) {
        Random random = new Random(42); // Mesma semente usada nos exercícios anteriores
        for (int i = 0; i < quantidadeValores; i++) {
            int valor = random.nextInt(1000000); // Gera números aleatórios entre 0 e 999999
            inserir(valor);
        }
    }

    public int getColisoes() {
        return colisoes;
    }

    public int getInsercoes() {
        return insercoes;
    }

    public int getMaiorLista() {
        return maiorLista;
    }

    public double getTamanhoMedioListas() {
        int totalElementos = 0;
        int listasNaoVazias = 0;
        for (LinkedList<Integer> lista : tabela) {
            if (!lista.isEmpty()) {
                totalElementos += lista.size();
                listasNaoVazias++;
            }
        }
        return listasNaoVazias > 0 ? (double) totalElementos / listasNaoVazias : 0;
    }

    public static void main(String[] args) {
        int tamanhoTabela = 257; // Mesmo tamanho usado nos exercícios anteriores
        int quantidadeValores = 180; // Mesma quantidade usada nos exercícios anteriores

        HashDivLinkedList hash = new HashDivLinkedList(tamanhoTabela);
        hash.realizarEnsaio(quantidadeValores);

        System.out.println("Resultados do ensaio (Hash com Listas Ligadas):");
        System.out.println("Tamanho da tabela: " + tamanhoTabela);
        System.out.println("Quantidade de valores inseridos: " + hash.getInsercoes());
        System.out.println("Colisões: " + hash.getColisoes());
        System.out.println("Tamanho da maior lista: " + hash.getMaiorLista());
        System.out.printf("Tamanho médio das listas: %.2f\n", hash.getTamanhoMedioListas());
    }
}