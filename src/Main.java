public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10);

        ItemBiblioteca l1 = new Livro("L01", "Clean Code", "Robert C. Martin");
        ItemBiblioteca r1 = new Revista("R01", "Superinteressante", 450);
        

        biblioteca.cadastrarItem(l1);
        biblioteca.cadastrarItem(r1);

        Usuario aluno = new Aluno("Carlos Silva");
        Usuario aluno2 = new Aluno("Ana Souza");

        System.out.println("=== CENÁRIO 1: Empréstimo Bem-Sucedido ===");
        biblioteca.emprestar(l1, aluno);

        System.out.println("\n=== CENÁRIO 2: Empréstimo Recusado por Limite Atingido ===");

        aluno.incrementarEmprestimo();
        aluno.incrementarEmprestimo();
        aluno.incrementarEmprestimo();
        
        biblioteca.emprestar(r1, aluno);

    }
}