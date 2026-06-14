package Service;
import java.util.*;
import Dao.AlunoDao;
import java.sql.*;
import Model.Aluno;

public class AlunoService {
    private AlunoDao dao;
    private Scanner sc;

    public AlunoService(AlunoDao dao , Scanner sc){
        this.dao = dao;
        this.sc = sc;
    }

    public void MenuAluno() throws SQLException{
        while(true){
            System.out.println("\n === Menu alunos === \n");
            System.out.println("\n 1 -> cadastrar ");
            System.out.println("\n 2 -> Listar ");
            System.out.println("\n 3 -> Atualizar ");
            System.out.println("\n 4 -> Deletar ");
            System.out.println("\n 5 -> Sair ");
            System.out.println("\n Digite opção desejada acima: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            try{
                switch(opcao){
                    case 1 -> CadastrarAluno();
                    case 2 -> ListarAlunos();
                    case 3 -> AlterAluno();
                    case 4 -> DeleteAluno();
                    case 5 -> { return; }
                    default -> System.out.println("Opção invalida, Tente novamente! ");
                }
            }catch(Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }

    public void CadastrarAluno() throws SQLException{
        System.out.println("\n --- Cadastro Alunos --- \n ");
        System.out.println("Digite a quantidade de alunos que deseja cadastrar? ");
        int qtd = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < qtd; i += 1){
            System.out.println("Digite nome do aluno: ");
            String Nome = sc.nextLine();
            System.out.println("Digite email do aluno(a) " + Nome + ": ");
            String Email = sc.nextLine();
            System.out.println("Informe idade do aluno(a) "+ Nome + " : ");
            int Idade = sc.nextInt();
            Aluno a = new Aluno(0 , Nome , Email , Idade);
            dao.InsertAlunos(a);
            System.out.println("Aluno "+ Nome + ", Cadastrado com sucesso!");
        }
    }

    public void ListarAlunos() throws SQLException{
        System.out.println(" ----- Alunos Disponíveis ----- ");
        List<Aluno> a = dao.listarAlunos();
        for(Aluno al : a){
            System.out.println(al);
        }
    }

    public void AlterAluno() throws SQLException {
        String Opcao;
        do{
            ListarAlunos();
            System.out.println("Informe o id do aluno que deseja alterar: ");
            int id = sc.nextInt();
            sc.nextLine();

            Aluno a = dao.BuscarId(id);
            if(a != null){
                System.out.println("Aluno não encontrado!");
                return;
            }
            System.out.println("Infome o nome atualizado: ");
            a.setNomeAluno(sc.nextLine());

            System.out.println("Infome email: ");
            a.setEmail(sc.nextLine());

            System.out.println("Informe a idade: ");
            a.setIdade(sc.nextInt());
            sc.nextLine();

            dao.UpdateAlunos(a);
            // uso get aqui e porquê foram enviados primeiro os set get pegou e agr get retorna os que pegaram do set que recebe
            System.out.println("Aluno atualizado com sucesso! " + "Nome: " + a.getNomeAluno() + ", Email: " + a.getEmail() + ", Idade: " + a.getIdade());


            System.out.println("Deseja Alterar proximo aluno(Sim/Não? ");
            Opcao = sc.nextLine();
        }while(Opcao.equalsIgnoreCase("sim"));

    }

    public void DeleteAluno() throws SQLException{
        String opcao;
        do{
            ListarAlunos();

            System.out.println("Informe o id do aluno que deseja deletar: ");
            int id = sc.nextInt();
            sc.nextLine();
            Aluno a = dao.BuscarId(id);
            if(a != null){
                System.out.println("Aluno não encontrado!");
                return;
            }

           System.out.println("Deseja deletar proximo aluno(Sim/Não)? ");
           opcao = sc.nextLine();

           System.out.println("Cadastro "+ id + ", Deletado com sucesso!");

           dao.DeletarAluno(id);

           System.out.println("o id "+ id +" do aluno "+ a.getNomeAluno() +" removido com sucesso!");

        }while(opcao.equalsIgnoreCase("sim"));
    }
}
