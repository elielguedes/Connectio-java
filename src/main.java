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
            sc.nextLine();

            for (int i = 0; i < qtd; i++) {
                System.out.println("\n--- Cadastro do aluno " + (i+1) + " ---");
                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Idade: ");
                int idade = sc.nextInt();
                sc.nextLine();

                // Inserir no banco
                String sql = "INSERT INTO Aluno(nome_aluno, email, idade) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setInt(3, idade);
                ps.executeUpdate();

                System.out.println("Alunos cadastrado com sucesso " + nome +" !");
            }
            // Cadastro professor
            System.out.println("Informe a quantidade de professores: ");
            int QtdProf = sc.nextInt();
            sc.nextLine();

            for(int i = 0; i < QtdProf ; i++){
                System.out.println("Informe o nome do professor "+ (i+1)+": ");
                String NomeProf = sc.nextLine();

                System.out.println("Informe a titulação do "+ NomeProf +": ");
                String titulacao = sc.nextLine();

                String sqlP = "INSERT INTO Professor(nome_professor , titulacao) values (? , ?)";
                PreparedStatement ps = conn.prepareStatement(sqlP);
                ps.setString(1 , NomeProf);
                ps.setString(2 , titulacao);
                ps.executeUpdate();

                System.out.println("Professores cadastrado com sucesso "+ NomeProf + " !");
            }

            //Cadastrar curso
            System.out.println("\n == Cadastrar curso ==");
            System.out.println("Digite o nome do curso: ");
            String NomeCurso = sc.nextLine();
            System.out.println("Informe a duração de "+ NomeCurso +": ");
            int Duracao = sc.nextInt();
            sc.nextLine();

            String sqlc = "INSERT INTO curso(nome_curso , duracao) values (? , ?)";
            PreparedStatement ps = conn.prepareStatement(sqlc);
            ps.setString(1 , NomeCurso);
            ps.setInt(2 , Duracao);
            ps.executeUpdate();
            System.out.println("Curso Cadastrado com sucesso "+ NomeCurso +" !");
            conexao.fechar();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
