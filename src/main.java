import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            Conneccao conexao = new Conneccao();
            conexao.conectar();
            Connection conn = conexao.getConnection();

            Scanner sc = new Scanner(System.in);

            System.out.print("Quantos alunos deseja cadastrar? ");
            int qtd = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            for (int i = 0; i < qtd; i++) {
                System.out.println("\n--- Cadastro do aluno " + (i+1) + " ---");
                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Idade: ");
                int idade = sc.nextInt();
                sc.nextLine(); // consumir quebra de linha

                // Inserir no banco
                String sql = "INSERT INTO Aluno(nome_aluno, email, idade) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setInt(3, idade);
                ps.executeUpdate();

                System.out.println("Aluno cadastrado com sucesso!");
            }

            conexao.fechar();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
