public class Livro extends ItemBiblioteca {
    private String autor;

    public Livro(String codigo, String titulo, String autor) {
        super(codigo, titulo);
        this.autor = autor;
    }

    @Override
    public int getPrazoDias() {
        return 14;
    }

    @Override
    public double getValorMultaDiaria() {
        return 0.50;
    }
}