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
            System.out.println("1 -> Cadastrar ");
            System.out.println("2 -> Listar ");
            System.out.println("3 -> Atualizar ");
            System.out.println("4 -> Deletar");
            System.out.println("5 -> Sair");

            System.out.println("Digite opção desejada acima: ");
            int opcao = sc.nextInt();
            sc.nextLine();
            try{
                switch(opcao){
                    case 1 -> CadastrarProfessor();
                    case 2 -> ListarProfessores();
                    case 3 -> UpdateProf();
                    case 4 -> DeletarProf();
                    case 5 -> { return; }
                    default -> System.out.println("Opção incorreta tente novamente!");
                }
            } catch(Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }

    public void CadastrarProfessor() throws SQLException {
        System.out.println("Informe a quantidade de Professor que deseja cadastrar: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        for(int i = 0;i < qtd; i += 1){
            System.out.println("Digite nome do professor " + (i+1) + " : ");
            String Nome = sc.nextLine();

            System.out.println("Informe a titulação "+ Nome + " : ");
            String titulacao = sc.nextLine();

            Professor p = new Professor(0 , Nome , titulacao);
            try{
                dao.InsertProf(p);
                System.out.println("Professor "+ Nome + ", Cadastrado com sucesso!");
            }catch(SQLException e){
                System.out.println("Erro no Cadastro do professor "+ Nome + " : "+ e.getMessage());
            }
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
            System.out.println("Digite o id do Professor que deseja alterar: ");
            int id = sc.nextInt();
            sc.nextLine();

            Professor p = dao.BuscaId(id);
            if(p != null){
                System.out.println("Usuario não encontrado!");
                return;
            }
            System.out.println("Digite novo nome do professor: ");
            p.setNome_professor(sc.nextLine());

            System.out.println("Informe a nova titulação "+ p.getNome_professor() + " : ");
            p.setTitulacao(sc.nextLine());
            dao.UpdateProfessor(p);

            System.out.println("Professor "+ p.getNome_professor()+ " titulção "+ p.getTitulacao()+" alterada com sucesso!");

            System.out.println("Deseja atualizar proximo cadastro(Sim/Não)? ");
            opcao = sc.nextLine();

        }while(opcao.equalsIgnoreCase("sim"));
    }

    public void DeletarProf() throws SQLException {
        String op;
        do {
            ListarProfessores();
            System.out.println("Digite o id do professor que deseja deletar: ");
            int id = sc.nextInt();
            sc.nextLine();
            Professor p = dao.BuscaId(id);
            if(p != null){
                System.out.println("Usuario não encontrado!");
            }
            dao.DelProfessores(id);


            System.out.println("Cadastro "+ id +" deletado com sucesso!");

            System.out.println("Deseja Deletar o proximo cadastro(Sim/Não)? ");
            op = sc.nextLine();

        }while(op.equalsIgnoreCase("sim"));
    }
}
