import java.util.LinkedList;

public class Tabuleiro {
    private LinkedList<Peca> pecasJogadas; // Usando LinkedList para facilitar inserções no início e no fim

    public Tabuleiro() {
        this.pecasJogadas = new LinkedList<>();
    }

    // Adiciona uma peça no final do tabuleiro
    public void adicionarPecaNoFinal(Peca peca) {
        pecasJogadas.addLast(peca);
    }

    // Adiciona uma peça no início do tabuleiro
    public void adicionarPecaNoInicio(Peca peca) {
        pecasJogadas.addFirst(peca);
    }

    // Pega a última peça jogada (no final do tabuleiro)
    public Peca getUltimaPeca() {
        return pecasJogadas.isEmpty() ? null : pecasJogadas.getLast();
    }

    // Pega a primeira peça jogada (no início do tabuleiro)
    public Peca getPrimeiraPeca() {
        return pecasJogadas.isEmpty() ? null : pecasJogadas.getFirst();
    }

      // Verifica se o tabuleiro está vazio
      public boolean estaVazio() {
        return pecasJogadas.isEmpty();
    }

    // Exibe o estado atual do tabuleiro
    public void mostrarTabuleiro() {
        System.out.println("Tabuleiro: " + pecasJogadas);
    }
}
