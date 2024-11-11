import java.util.Scanner;

public class HashDivLinearProbe {
    private int[] tabela;
    private final int TAMANHO = 7; // Tamanho ímpar predefinido

    public HashDivLinearProbe() {
        this.tabela = new int[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            tabela[i] = -1; // Inicializa todas as posições com -1 (vazio)
        }
    }

    private int funcaoHash(int chave) {
        return chave % TAMANHO;
    }

    public void inserir(int chave) {
        int indice = funcaoHash(chave);
        int tentativas = 0;

        while (tabela[indice] != -1 && tentativas < TAMANHO) {
            indice = (indice + 1) % TAMANHO; // Método linear para tratar colisões
            tentativas++;
        }

        if (tentativas == TAMANHO) {
            System.out.println("Erro: Tabela cheia. Não foi possível inserir " + chave);
        } else {
            tabela[indice] = chave;
            System.out.println("Chave " + chave + " inserida no índice " + indice);
        }
    }

    public void exibirTabela() {
        System.out.println("\nConteúdo da Tabela Hash:");
        for (int i = 0; i < TAMANHO; i++) {
            if (tabela[i] != -1) {
                System.out.println("Índice " + i + ": " + tabela[i]);
            } else {
                System.out.println("Índice " + i + ": vazio");
            }
        }
    }

    public static void main(String[] args) {
        HashDivLinearProbe hash = new HashDivLinearProbe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tabela Hash com tamanho " + hash.TAMANHO);

        while (true) {
            System.out.print("Digite um valor para inserir (ou -1 para sair): ");
            int valor = scanner.nextInt();

            if (valor == -1) {
                break;
            }

            hash.inserir(valor);
        }

        hash.exibirTabela();
        scanner.close();
    }
}