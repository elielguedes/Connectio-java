package Service;

import java.util.*;
import java.sql.*;
import Model.Curso;
import Dao.CursoDao;

public class CursoService {
    private CursoDao dao;
    private Scanner sc;

    public CursoService(CursoDao dao , Scanner sc){
        this.dao = dao;
        this.sc = sc;
    }

    public void MenuCurso() throws SQLException{
        while(true){
            System.out.println("\n === Menu Curso === \n ");
            System.out.println("\n 1 -> Cadatrar ");
            System.out.println("\n 2 -> Listar");
            System.out.println("\n 3 -> Atualizar");
            System.out.println("\n 4 -> Deletar");
            System.out.println("\n 5 -> Sair ");
            System.out.println("\nDigite a opção desejada acima: ");

            int op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1 -> CadastrarCurso();
                case 2 -> ListarCursos();
                case 3 -> UpdateCurso();
                case 4 -> DeletarCurso();
                case 5 -> { return; }
            }
        }
    }

    public void CadastrarCurso() throws SQLException {
        System.out.println("Digite quantos Cursos deseja cadastrar: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        for(int i = 0;i < qtd;i++){
            System.out.println("Digite o nome do curso: ");
            String Name = sc.nextLine();
            System.out.println("Informe a duração do "+ Name +" : ");
            int duracao = sc.nextInt();
            sc.nextLine();

            Curso c = new Curso(0 , Name , duracao);
            dao.InserirCurso(c);
            System.out.println("O curso "+ Name +" Cadastrado com sucessp!");
        }
    }

    public void ListarCursos() throws SQLException {
        List<Curso> c = dao.ListarCurso();
        for(Curso cp : c){
            System.out.println(cp);
        }
    }

    public void UpdateCurso() throws SQLException{
        String opcao;
        do{
            ListarCursos();
            System.out.println("Informe o id do curso que deseja alterar: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Informe o novo nome do curso: ");
            String Name = sc.nextLine();
            System.out.println("Digite a nova duração do "+ Name + " : ");
            int duracao = sc.nextInt();
            sc.nextLine();

            Curso c = new Curso(id, Name , duracao);
            dao.UpdateCurso(c);
            System.out.println("O curso "+ Name +" Alterado com sucesso!");

            System.out.println("Deseja Alterar proximo cadastro? ");
            opcao = sc.nextLine();
        }while(opcao.equalsIgnoreCase("sim"));
    }

    public void DeletarCurso() throws SQLException {
        String op;
        do {
            ListarCursos();
            System.out.println("Informe o id do curso que deseja deletar: ");
            int id = sc.nextInt();
            sc.nextLine();
            dao.DeleteCurso(id);
            System.out.println("id "+ id+" deletado com sucesso ");

            System.out.println("Digite se deseja alterar o proximo cadastro: ");
            op = sc.nextLine();
        }while(op.equalsIgnoreCase("sim"));
    }
}