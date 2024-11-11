import java.util.Random;

public class hashMultLinearProbe {
    private int[] tabela;
    private int tamanho;
    private int colisoes;
    private int insercoes;
    private static final double A = (Math.sqrt(5) - 1) / 2; // Constante para o método da multiplicação

    public hashMultLinearProbe(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new int[tamanho];
        this.colisoes = 0;
        this.insercoes = 0;
        
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = -1;
        }
    }

    private int funcaoHash(int chave) {
        double produto = chave * A;
        double parteDecimal = produto - Math.floor(produto);
        return (int) (tamanho * parteDecimal);
    }

    public boolean inserir(int chave) {
        int indice = funcaoHash(chave);
        int tentativas = 0;
        while (tabela[indice] != -1) {
            if (tentativas >= tamanho) {
                return false; // Tabela cheia
            }
            colisoes++;
            indice = (indice + 1) % tamanho;
            tentativas++;
        }
        tabela[indice] = chave;
        insercoes++;
        return true;
    }

    public void realizarEnsaio(int quantidadeValores) {
        Random random = new Random(42); // Semente fixa
        for (int i = 0; i < quantidadeValores; i++) {
            int valor = random.nextInt(1000000); // Gera números aleatórios entre 0 e 999999
            boolean inserido = inserir(valor);
            if (!inserido) {
                System.out.println("Tabela cheia após " + i + " inserções.");
                break;
            }
        }
    }

    public int getColisoes() {
        return colisoes;
    }

    public int getInsercoes() {
        return insercoes;
    }

    public static void main(String[] args) {
        int tamanhoTabela = 257; // Número ímpar
        int quantidadeValores = 180;

        hashMultLinearProbe hash = new hashMultLinearProbe(tamanhoTabela);
        hash.realizarEnsaio(quantidadeValores);

        System.out.println("Resultados do ensaio (Método da Multiplicação com Linear Probing):");
        System.out.println("Tamanho da tabela: " + tamanhoTabela);
        System.out.println("Quantidade de valores tentados: " + quantidadeValores);
        System.out.println("Inserções: " + hash.getInsercoes());
        System.out.println("Colisões: " + hash.getColisoes());
    }
}