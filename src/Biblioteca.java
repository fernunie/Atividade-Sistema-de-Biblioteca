public class Biblioteca {
    private ItemBiblioteca[] acervo;
    private int totalItens;

    public Biblioteca(int capacidadeItens) {
        acervo = new ItemBiblioteca[capacidadeItens];
        totalItens = 0;
    }

    public boolean cadastrarItem(ItemBiblioteca item) {
        if (totalItens < acervo.length) {
            acervo[totalItens++] = item;
            return true;
        }
        return false;
    }

    public boolean emprestar(ItemBiblioteca item, Usuario usuario) {
        if (!item.isDisponivel()) {
            System.out.println("Empréstimo recusado: O item '" + item.getTitulo() + "' não está disponível.");
            return false;
        }

        if (usuario.getQuantidadeEmprestada() >= usuario.getLimiteItens()) {
            System.out.println("Empréstimo recusado para " + usuario.getNome() + ": Limite de itens atingido (" + usuario.getLimiteItens() + ").");
            return false;
        }

        item.setDisponivel(false);
        usuario.incrementarEmprestimo();
        System.out.println("Sucesso: Empréstimo de '" + item.getTitulo() + "' realizado para " + usuario.getNome() + ".");
        System.out.println("Prazo de devolução: " + item.getPrazoDias() + " dias | Multa diária: R$ " + item.getValorMultaDiaria());
        return true;
    }

    public boolean devolver(ItemBiblioteca item, Usuario usuario) {
        if (item.isDisponivel()) {
            System.out.println("Erro: Este item já consta como disponível.");
            return false;
        }

        item.setDisponivel(true);
        usuario.decrementarEmprestimo();
        System.out.println("Devolução realizada com sucesso para o item: " + item.getTitulo());
        return true;
    }

    
    public void listarAcervo() {
        System.out.println("\n--- ACERVO DA BIBLIOTECA ---");
        for (int i = 0; i < totalItens; i++) {
            ItemBiblioteca item = acervo[i];
            String status = item.isDisponivel() ? "Disponível" : "Emprestado";
            System.out.println("Código: " + item.getCodeOrCodigo() + " | Título: " + item.getTitulo() + " | Status: " + status);
        }
    }
}