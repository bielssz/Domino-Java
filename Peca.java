public class Peca {
    private int lado1;
    private int lado2;

    public Peca(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    public int getLado1() {
        return lado1;
    }

    public int getLado2() {
        return lado2;
    }

    public boolean combinaCom(Peca outraPeca) {
        return this.lado1 == outraPeca.lado1 || this.lado1 == outraPeca.lado2 ||
               this.lado2 == outraPeca.lado1 || this.lado2 == outraPeca.lado2;
    }

    @Override
    public String toString() {
        return "[" + lado1 + "|" + lado2 + "]";
    }
}
