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
            switch(opcao){
                case 1 -> CadastrarAluno();
                case 2 -> ListarAlunos();
                case 3 -> AlterAluno();
                case 4 -> DeleteAluno();
                case 5 -> { return; }
                default -> System.out.println("Opção invalida, Tente novamente! ");
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

            System.out.println("Infome o nome atualizado: ");
            String Name = sc.nextLine();
            System.out.println("Infome email: ");
            String Email = sc.nextLine();
            System.out.println("Informe a idade: ");
            int Idade = sc.nextInt();
            sc.nextLine();
            Aluno al = new Aluno(id , Name , Email , Idade);
            dao.UpdateAlunos(al);
            System.out.println("Aluno, "+ Name + " email, "+ Email + " Idade, "+ Idade + ", Alterado com sucesso!");

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

           System.out.println("Deseja deletar proximo aluno(Sim/Não)? ");
           opcao = sc.nextLine();

           System.out.println("Cadastro "+ id + ", Deletado com sucesso!");

           dao.DeletarAluno(id);

        }while(opcao.equalsIgnoreCase("sim"));
    }
}
