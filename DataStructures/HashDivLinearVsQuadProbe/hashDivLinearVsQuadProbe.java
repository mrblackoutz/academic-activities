import java.util.Random;

public class hashDivLinearVsQuadProbe {
    private int[] tabelaQuadratica;
    private int[] tabelaLinear;
    private int tamanho;
    private int colisoesQuadratica;
    private int colisoesLinear;
    private int insercoesQuadratica;
    private int insercoesLinear;

    public hashDivLinearVsQuadProbe(int tamanho) {
        this.tamanho = tamanho;
        this.tabelaQuadratica = new int[tamanho];
        this.tabelaLinear = new int[tamanho];
        this.colisoesQuadratica = 0;
        this.colisoesLinear = 0;
        this.insercoesQuadratica = 0;
        this.insercoesLinear = 0;
        
        for (int i = 0; i < tamanho; i++) {
            tabelaQuadratica[i] = -1;
            tabelaLinear[i] = -1;
        }
    }

    private int funcaoHash(int chave) {
        return chave % tamanho;
    }

    public boolean inserirQuadratica(int chave) {
        int indice = funcaoHash(chave);
        int i = 0;
        while (tabelaQuadratica[indice] != -1) {
            if (i >= tamanho) {
                return false; // Tabela cheia
            }
            colisoesQuadratica++;
            i++;
            indice = (funcaoHash(chave) + i*i) % tamanho;
        }
        tabelaQuadratica[indice] = chave;
        insercoesQuadratica++;
        return true;
    }

    public boolean inserirLinear(int chave) {
        int indice = funcaoHash(chave);
        int tentativas = 0;
        while (tabelaLinear[indice] != -1) {
            if (tentativas >= tamanho) {
                return false; // Tabela cheia
            }
            colisoesLinear++;
            indice = (indice + 1) % tamanho;
            tentativas++;
        }
        tabelaLinear[indice] = chave;
        insercoesLinear++;
        return true;
    }

    public void realizarEnsaio(int quantidadeValores) {
        Random random = new Random(42);
        for (int i = 0; i < quantidadeValores; i++) {
            int valor = random.nextInt(1000); // Gera números aleatórios entre 0 e 999
            boolean inseridoQuad = inserirQuadratica(valor);
            boolean inseridoLin = inserirLinear(valor);
            if (!inseridoQuad || !inseridoLin) {
                System.out.println("Tabela cheia após " + i + " inserções.");
                break;
            }
        }
    }

    public int getColisoesQuadratica() {
        return colisoesQuadratica;
    }

    public int getColisoesLinear() {
        return colisoesLinear;
    }

    public int getInsercoesQuadratica() {
        return insercoesQuadratica;
    }

    public int getInsercoesLinear() {
        return insercoesLinear;
    }

    public static void main(String[] args) {
        int tamanhoTabela = 257; // Número ímpar
        int quantidadeValores = 180;

        hashDivLinearVsQuadProbe hash = new hashDivLinearVsQuadProbe(tamanhoTabela);
        hash.realizarEnsaio(quantidadeValores);

        System.out.println("Resultados do ensaio:");
        System.out.println("Tamanho da tabela: " + tamanhoTabela);
        System.out.println("Quantidade de valores tentados: " + quantidadeValores);
        System.out.println("Sondagem quadrática:");
        System.out.println("Inserções: " + hash.getInsercoesQuadratica());
        System.out.println("Colisões: " + hash.getColisoesQuadratica());
        System.out.println("Sondagem linear:");
        System.out.println("Inserções: " + hash.getInsercoesLinear());
        System.out.println("Colisões: " + hash.getColisoesLinear());
    }
}