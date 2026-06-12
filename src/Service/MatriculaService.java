package Service;
import java.sql.*;
import java.util.*;
import Dao.MatriculaDao;
import Dao.AlunoDao;
import Dao.DisciplinaDao;
import Model.Aluno;
import Model.Disciplina;
import Model.Matricula;

public class MatriculaService {
    private Connection c;
    private Scanner sc;
    private AlunoDao a;
    private DisciplinaDao d;
    private MatriculaDao dao;

    public MatriculaService(Connection c , Scanner sc , AlunoDao a , DisciplinaDao d , MatriculaDao dao){
        this.c = c;
        this.sc = sc;
        this.a = a;
        this.d = d;
        this.dao = dao;
    }

    public void Menu() throws SQLException{
        while(true){
            System.out.println("1 - Cadatrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Apaguar");
            System.out.println("5 - Sair");

            System.out.println("Digite opção desejada acima: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            try{
                switch(opcao){
                    case 1 -> CadastrarMatricula();
                    case 2 -> ListMatricula();
                    case 3 -> Update();
                    case 4 -> Deletar();
                    case 5 -> {return ;}
                    default -> System.out.println("Opção invalida tente novamente");
                }
            }catch(Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
    }

    public void ListarAluno() throws SQLException {
        List<Aluno> al = a.listarAlunos();
        for(Aluno as : al){
            System.out.println(as);
        }
    }

    public void ListarDisciplina() throws SQLException {
        List<Disciplina> di = d.ListarDisciplina();
        for(Disciplina disc : di){
            System.out.println(disc);
        }
    }

    public void CadastrarMatricula() throws SQLException {
        System.out.println("Informe a quantida de matricula que deseja cadastrar: ");
        int qtd = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < qtd ; i++){
            System.out.println("Digite o status da matricula: ");
            String status = sc.nextLine();

            System.out.println("\n -= Alunos Cadastrados =- \n");
            ListarAluno();

            System.out.println("Digite id do aluno que deseja matricular: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("\n -= Disciplinas disponíveis =- \n");
            ListarDisciplina();

            System.out.println("Informe id da matricula qua deseja cadastrar na matricula: ");
            int iddis = sc.nextInt();
            sc.nextLine();

            Matricula m = new Matricula(0 , status , id , iddis);
            dao.InsertMatricula(m);
        }
    }

    public void ListMatricula() throws SQLException {
        List<Matricula> ma = dao.ListarMatricula();
        for(Matricula m : ma){
            System.out.println(m);
        }
    }

    public void Update() throws SQLException {
        String op;
        do{
            ListMatricula();

            System.out.println("digite o id que deseja alterar: ");
            int id = sc.nextInt();
            sc.nextLine();

            Matricula m = dao.Busca(id);
            if(m != null)
                System.out.println("Cadastro não encontrado !!");

            System.out.println("Digite o novo status: ");
            assert m != null;
            m.setStatus(sc.nextLine());

            System.out.println("\n =- Alunos -= \n");
            ListarAluno();

            System.out.println("Digite o id novo do cadastro do aluno que deseja alterar: ");
            m.setAlunofk(sc.nextInt());
            sc.nextLine();

            System.out.println("\n =- Disciplinas -= \n");
            ListarDisciplina();

            System.out.println("Infome id da nova disciplina: ");
            m.setDisciplinafk(sc.nextInt());
            sc.nextLine();

            dao.UpdateMatricula(m);

            System.out.println("Deseja alterar mais algum cadastro(sim/não)? ");
            op = sc.nextLine();

        }while(op.equalsIgnoreCase("sim"));
    }

    public void Deletar() throws SQLException {
        String opcao;
        do{
            System.out.println("\n -= Matriculas =- \n");
            ListMatricula();
            System.out.println("Digite o id que deseja deletar: ");
            int id_matricula = sc.nextInt();
            sc.nextLine();

            Matricula ma = dao.Busca(id_matricula);
            if(ma != null){
                System.out.println("Cadastro não encontrado tente novamente!");
                return;
            }

            dao.DeleteMatricula(ma);

            System.out.println("Deseja deletar masi algum cadastro(sim/não): ");
            opcao = sc.nextLine();
        }while(opcao.equalsIgnoreCase("sim"));

    }

}
