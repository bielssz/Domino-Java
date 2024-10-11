import java.util.ArrayList;

public class Jogador {
    private String nome;
    private ArrayList<Peca> pecas;

    public Jogador(String nome) {
        this.nome = nome;
        this.pecas = new ArrayList<>();
    }

    public void adicionarPeca(Peca peca) {
        pecas.add(peca);
    }

    public boolean removerPeca(Peca peca) {
        return pecas.remove(peca);
    }

    public ArrayList<Peca> getPecas() {
        return pecas;
    }

    public String getNome() {
        return nome;
    }

    // Exibir as peças do jogador
    public void mostrarPecas() {
        System.out.println(nome + ": " + pecas);
    }
}
