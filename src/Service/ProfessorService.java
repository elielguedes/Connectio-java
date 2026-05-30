package Service;

import Model.Professor;
import Dao.ProfessorDao;
import java.sql.*;
import java.util.*;

public class ProfessorService {
    private ProfessorDao dao;
    private Scanner sc;

    public ProfessorService(ProfessorDao dao , Scanner sc){
        this.dao = dao;
        this.sc = sc;
    }

    public void MenuProf() throws SQLException{
        while(true){
            System.out.println("\n === Menu Professor === \n ");
            System.out.println("\n1 -> Cadastrar ");
            System.out.println("\n2 -> Listar ");
            System.out.println("\n3 -> Atualizar ");
            System.out.println("\n4 -> Deletar");
            System.out.println("\n5 -> Sair");

            System.out.println("Digite opção desejada acima: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1 -> CadastrarProfessor();
                case 2 -> ListarProfessores();
                case 3 -> UpdateProf();
                case 4 -> DeletarProf();
                case 5 -> { return; }
                default -> System.out.println("Opção incorreta tente novamente!");
            }
        }
    }

    public void CadastrarProfessor() throws SQLException {
        System.out.println("Informe a quantidade de Professor que deseja cadastrar: ");
        int qtd = sc.nextInt();
        for(int i = 0;i < qtd; i += 1){
            System.out.println("Digite nome do professor: ");
            String Nome = sc.nextLine();

            System.out.println("Informe a titulação "+ Nome + " : ");
            String titulacao = sc.nextLine();

            Professor p = new Professor(0 , Nome , titulacao);
            System.out.println("Professor "+ Nome + ", Cadastrado com sucesso!");
        }
    }

    public void ListarProfessores() throws SQLException{
        List<Professor> p = dao.ListProf();
        for(Professor pr : p){
            System.out.println(pr);
        }
    }

    public void UpdateProf() throws SQLException{
        String opcao;
        do {
            ListarProfessores();
            System.out.println("Digite o do Professor que deseja alterar: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite novo nome do professor: ");
            String Professor = sc.nextLine();

            System.out.println("Informe a nova titulação "+ Professor + " : ");
            String title = sc.nextLine();

            System.out.println("Deseja atualizar proximo cadastro(Sim/Não)? ");
            opcao = sc.nextLine();

            Professor p = new Professor(id , Professor , title);
            dao.UpdateProfessor(p);
        }while(opcao.equalsIgnoreCase("sim"));
    }

    public void DeletarProf() throws SQLException {
        String op;
        do {
            ListarProfessores();
            System.out.println("Digite o id do professor que deseja deletar: ");
            int id = sc.nextInt();
            sc.nextLine();
            dao.DelProfessores(id);


            System.out.println("Cadastro "+ id +" deletado com sucesso!");

            System.out.println("Deseja Deletar o proximo cadastro(Sim/Não)? ");
            op = sc.nextLine();

        }while(op.equalsIgnoreCase("sim"));
    }
}
