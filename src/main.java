import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

public class main {
    public static void main(String[] args) {
        try {
            Conneccao conexao = new Conneccao();
            conexao.conectar();
            Connection conn = conexao.getConnection();
            AlunoDao d = new AlunoDao(conn);
            ProfessorDao Pd = new ProfessorDao(conn);
            CursoDao cd = new CursoDao(conn);

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
                Aluno a = new Aluno(0 , nome , email , idade);
                d.InserirAluno(a);

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

                Professor p = new Professor(0 , NomeProf , titulacao);
                Pd.InserirProf(p);

                System.out.println("Professores cadastrado com sucesso "+ NomeProf + " !");
            }

            //Cadastrar curso
            System.out.println("\n == Cadastrar curso ==");
            System.out.println("Digite o nome do curso: ");
            String NomeCurso = sc.nextLine();
            System.out.println("Informe a duração de "+ NomeCurso +": ");
            int Duracao = sc.nextInt();
            sc.nextLine();

            Curso c = new Curso(0 , NomeCurso , Duracao);
            cd.InserirCurso(c);

            System.out.println("Digite a quantidade de disciplina: ");
            int qtdD = sc.nextInt();
            sc.nextLine();
            for(int y = 0; y < qtdD ;y++){
                System.out.println("Informe o nome da disciplina " + (y + 1) + ": ");
                String NomeDisciplina = sc.nextLine();
                System.out.println("Informe a carga horaria da Disciplina "+ NomeDisciplina + ": ");
                int Carga = sc.nextInt();
                sc.nextLine();

                List<Professor> ListaProf = Pd.ListarProfessor();
                System.out.println("\n ------ Professores Disponiveis -------- \n");
                for(Professor prof : ListaProf){
                    System.out.println(prof);
                }

                System.out.println("Informe Id do professor responsável pela "+ NomeDisciplina +": ");
                int IdP = sc.nextInt();
                sc.nextLine();


            }

            System.out.println("Curso Cadastrado com sucesso "+ NomeCurso +" !");
            conexao.fechar();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
