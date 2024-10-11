import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jogo {
    private ArrayList<Peca> pecas;
    private ArrayList<Jogador> jogadores;
    private Tabuleiro tabuleiro;

    public Jogo() {
        this.pecas = criarTodasPecas();
        this.jogadores = new ArrayList<>();
        this.tabuleiro = new Tabuleiro();
        inicializarJogadores();  // Adicionamos jogadores
    }

    // Método para inicializar até 4 jogadores
    private void inicializarJogadores() {
        Scanner scanner = new Scanner(System.in);

        // Pergunta quantos jogadores vão jogar (máximo 4)
        System.out.print("Quantos jogadores irão jogar (2-4)? ");
        int numJogadores = scanner.nextInt();

        // Garantir que o número de jogadores seja entre 2 e 4
        while (numJogadores < 2 || numJogadores > 4) {
            System.out.print("Número inválido. Escolha entre 2 e 4 jogadores: ");
            numJogadores = scanner.nextInt();
        }

        // Criar os jogadores
        for (int i = 1; i <= numJogadores; i++) {
            System.out.print("Digite o nome do Jogador " + i + ": ");
            String nome = scanner.next();
            jogadores.add(new Jogador(nome));
        }

        // Distribuir as peças entre os jogadores
        distribuirPecas();
    }

    // Método para criar todas as peças do jogo
    private ArrayList<Peca> criarTodasPecas() {
        ArrayList<Peca> todasPecas = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                todasPecas.add(new Peca(i, j));
            }
        }
        return todasPecas;
    }

    // Método para distribuir peças aleatoriamente para os jogadores
    private void distribuirPecas() {
        Collections.shuffle(pecas);
        int numJogadores = jogadores.size();
        for (int i = 0; i < pecas.size(); i++) {
            jogadores.get(i % numJogadores).adicionarPeca(pecas.get(i));
        }
    }

    // Método para iniciar o jogo
    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);
        boolean jogoTerminado = false;

        // Loop de rodadas
        while (!jogoTerminado) {
            for (Jogador jogador : jogadores) {
                System.out.println("\nTurno de " + jogador.getNome() + ":");
                jogador.mostrarPecas();

                // O jogador tenta jogar uma peça
                boolean jogadaValida = jogarPeca(jogador, tabuleiro, scanner);

                if (jogadaValida) {
                    if (jogador.getPecas().isEmpty()) {
                        System.out.println(jogador.getNome() + " venceu o jogo!");
                        jogoTerminado = true;
                        break;
                    }
                }
            }
        }

        scanner.close();
    }

    // Método para o jogador tentar jogar uma peça no tabuleiro
    private boolean jogarPeca(Jogador jogador, Tabuleiro tabuleiro, Scanner scanner) {
        tabuleiro.mostrarTabuleiro();

        System.out.print("Escolha o índice da peça para jogar (ou -1 para passar): ");
        int indice = scanner.nextInt();

        if (indice == -1) {
            System.out.println(jogador.getNome() + " passou a vez.");
            return false;
        }

        if (indice < 0 || indice >= jogador.getPecas().size()) {
            System.out.println("Índice inválido. Tente novamente.");
            return false;
        }

        Peca pecaEscolhida = jogador.getPecas().get(indice);

        if (tabuleiro.estaVazio()) {
            if (pecaEscolhida.getLado1() != pecaEscolhida.getLado2()) {
                System.out.println("A primeira peça deve ser uma dúplice. Escolha outra peça.");
                return false;
            } else {
                tabuleiro.adicionarPecaNoFinal(pecaEscolhida);
                jogador.removerPeca(pecaEscolhida);
                System.out.println(jogador.getNome() + " jogou a primeira peça: " + pecaEscolhida);
            }
        } else {
            System.out.print("Deseja jogar no início (1) ou no final (2) do tabuleiro? ");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                Peca primeiraPeca = tabuleiro.getPrimeiraPeca();
                if (pecaEscolhida.combinaCom(primeiraPeca)) {
                    tabuleiro.adicionarPecaNoInicio(pecaEscolhida);
                    jogador.removerPeca(pecaEscolhida);
                    System.out.println(jogador.getNome() + " jogou: " + pecaEscolhida + " no início do tabuleiro.");
                } else {
                    System.out.println("Peça não combina com a primeira peça. Tente outra.");
                    return false;
                }
            } else if (escolha == 2) {
                Peca ultimaPeca = tabuleiro.getUltimaPeca();
                if (pecaEscolhida.combinaCom(ultimaPeca)) {
                    tabuleiro.adicionarPecaNoFinal(pecaEscolhida);
                    jogador.removerPeca(pecaEscolhida);
                    System.out.println(jogador.getNome() + " jogou: " + pecaEscolhida + " no final do tabuleiro.");
                } else {
                    System.out.println("Peça não combina com a última peça. Tente outra.");
                    return false;
                }
            } else {
                System.out.println("Escolha inválida. Tente novamente.");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.iniciarJogo();
    }
}
