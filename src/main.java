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

                System.out.println("Deseja atualizar o cadastro " + nome + " (Sim/Não): ");
                String resposta = sc.nextLine();
                if(resposta.equalsIgnoreCase("sim")){
                    System.out.println("Digite novo nome: ");
                    String NovoNome = sc.nextLine();

                    System.out.println("Digite o novo email: ");
                    String NovoEmail = sc.nextLine();

                    System.out.println("Digite a nova idade: ");
                    int NovaIdade = sc.nextInt();
                    sc.nextLine();

                    List<Aluno> alunos = d.ListarAlunos();
                    System.out.println("\n --- Alunos disponiveis --- \n ");
                    for(Aluno al : alunos){
                        System.out.println(al);
                    }

                    System.out.println("Digite o id do aluno que deseja alterar: ");
                    int id_aluno = sc.nextInt();
                    sc.nextLine();

                    Aluno AlunoUpdate = new Aluno(id_aluno , NovoNome , NovoEmail , NovaIdade);
                    d.AtualizarAluno(AlunoUpdate);

                    System.out.println("cadastro "+ NovoNome +" atualizado com sucesso !");
                }

                System.out.println("Deseja deletar o cadastro (Sim/Não): ");
                String Res = sc.nextLine();

                if(Res.equalsIgnoreCase("sim")){
                    System.out.println("\n ---- Alunos cadastrados ---- \n");
                    List<Aluno> as = d.ListarAlunos();
                    for(Aluno al : as){
                        System.out.println(al);
                    }

                    System.out.println("Informe o id para deletar o cadastro: ");
                    int id_aluno_d = sc.nextInt();
                    sc.nextLine();

                    d.DeletarAluno(id_aluno_d);
                    System.out.println("cadastro de aluno deletado com sucesso !");
                }

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

                // Update professor
                System.out.println("Deseja alterar o cadastro "+ NomeProf + " (Sim/Não): ");
                String ResProf = sc.nextLine();
                if(ResProf.equalsIgnoreCase("sim")){
                    System.out.println("Informe o novo nome do "+ NomeProf + ": ");
                    String NovoNomeP = sc.nextLine();

                    System.out.println("Digite a nova titulação "+ titulacao + ": ");
                    String NovaTitulacao = sc.nextLine();

                    List<Professor> ListaProf = Pd.ListarProfessor();
                    System.out.println("\n ------ Professores Disponiveis -------- \n");
                    for(Professor prof : ListaProf){
                        System.out.println(prof);
                    }
                    System.out.println("Informe o id que deseja alterar: ");
                    int id_prof = sc.nextInt();
                    sc.nextLine();

                    Professor profup = new Professor(id_prof, NovoNomeP , NovaTitulacao);
                    Pd.UpdateProf(profup);
                }

                // Del professor
                System.out.println("Deseja apaguar cadastro do professor? ");
                String Res = sc.nextLine();
                if(Res.equalsIgnoreCase("sim")){
                    List<Professor> pr = Pd.ListarProfessor();
                    for(Professor profs : pr){
                        System.out.println(profs);
                    }
                    System.out.println("Infome o id do cadastro que deseja deletar: ");
                    int Del = sc.nextInt();
                    sc.nextLine();

                    Pd.DelProf(Del);
                    System.out.println("O cadastro "+ NomeProf + " de id "+ Del +" foi deletado com sucesso !");
                }
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
            }

            System.out.println("Curso Cadastrado com sucesso "+ NomeCurso +" !");
            conexao.fechar();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
