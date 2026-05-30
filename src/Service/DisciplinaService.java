package Service;

import Dao.CursoDao;
import Dao.ProfessorDao;
import Model.Professor;
import Model.Disciplina;
import Model.Curso;
import java.util.*;
import java.sql.*;
import Dao.DisciplinaDao;

public class DisciplinaService {
    private DisciplinaDao dao;
    private Scanner sc;
    private ProfessorDao p;
    private CursoDao c;

    public DisciplinaService(DisciplinaDao dao , Scanner sc){
        this.sc = sc;
        this.dao = dao;
        this.p = p;
        this.c = c;
    }

    public void MenuDisciplina() throws SQLException{
        while(true){
            System.out.println("\n === Menu Disciplina == \n ");
            System.out.println("\n1 -> Cadastrar");
            System.out.println("\n2 -> Listar ");
            System.out.println("\n3 -> Atualizar ");
            System.out.println("\n4 -> Deletar ");
            System.out.println("\n5 ->  Sair");
            System.out.println("\nDigite opção desejada acima: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1 -> CadastrarDisciplina();
                case 2 -> ListarDisciplinas();
                case 3 -> UpdateDisciplina();
                case 4 -> DeletarDisciplina();
                case 5 -> { return; }
                default -> System.out.println("Opção Invalida ou incorreta tente novamente ");
            }
        }
    }

    public void Lista() throws SQLException{
        List<Professor> pr = p.ListProf();
        for(Professor prof : pr){
            System.out.println(prof);
        }
    }

    public void ListaCurso() throws SQLException{
        List<Curso> cc = c.ListarCurso();
        for(Curso cur : cc){
            System.out.println(cur);
        }
    }

    public void CadastrarDisciplina() throws SQLException{
        System.out.println("Digite a quantidade de Disciplina quer cadastrar: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < qtd;i++){
            System.out.println("Digite o nome da disciplina: ");
            String Name = sc.nextLine();
            System.out.println("Digite a Carga horaria da disciplina "+ Name + " : ");
            int carga = sc.nextInt();
            sc.nextLine();
            Lista();
            System.out.println("Escolha o id do professor que ministrara a disciplina: ");
            int Prof_id = sc.nextInt();
            sc.nextLine();

            ListaCurso();
            System.out.println("Digite o id do curso que a disciplina "+ Name + " está atrelada: ");
            int curso_id = sc.nextInt();
            sc.nextLine();

            Disciplina d = new Disciplina(0 , Name , carga , Prof_id , curso_id);
            dao.InserirDisciplina(d);
        }
    }

    public void ListarDisciplinas() throws SQLException{
        List<Disciplina> d = dao.ListarDisciplina();
        for(Disciplina dd : d){
            System.out.println(dd);
        }
    }

    public void UpdateDisciplina() throws SQLException{
        String op;
        do{
            ListarDisciplinas();
            System.out.println("digite o id da disciplina que deseja alterar: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o nome da nova disciplina: ");
            String NewName = sc.nextLine();
            System.out.println("Digite a Carga horaria da disciplina "+ NewName + " : ");
            int carga = sc.nextInt();
            sc.nextLine();
            Lista();
            System.out.println("Escolha o id do professor que ministrara a disciplina: ");
            int Prof_id = sc.nextInt();
            sc.nextLine();

            ListaCurso();
            System.out.println("Digite o id do curso que a disciplina "+ NewName + " está atrelada: ");
            int curso_id = sc.nextInt();
            sc.nextLine();

            Disciplina dis = new Disciplina(id , NewName , carga , Prof_id , curso_id);
            dao.UpdateDisciplina(dis);

            System.out.println("Deseja Alterar mais um cadatro(Sim/não)? ");
            op = sc.nextLine();
        }while(op.equalsIgnoreCase("sim"));
    }

    public void DeletarDisciplina() throws SQLException {
        String op;
        do{
            ListarDisciplinas();
            System.out.println("Digite o id do cadastro que desja deletar: ");
            int id = sc.nextInt();
            sc.nextLine();
            dao.DelDisciplina(id);

            System.out.println("Digite se deseja deletar mais um cadatro: ");
            op = sc.nextLine();
        }while(op.equalsIgnoreCase("sim"));
    }

}
