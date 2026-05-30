import java.sql.*;
import java.util.*;
import Util.Conneccao;
import Service.AlunoService;
import Service.CursoService;
import Service.DisciplinaService;
import Service.ProfessorService;
import Dao.AlunoDao;
import Dao.ProfessorDao;
import Dao.CursoDao;
import Dao.DisciplinaDao;


public class main {
    public static void main(String[] args) {
        try {
            Conneccao conexao = new Conneccao();
            conexao.conectar();
            Connection conn = conexao.getConnection();

            Scanner sc = new Scanner(System.in);
            AlunoService as = new AlunoService(new AlunoDao(conn), sc);
            ProfessorService pf = new ProfessorService(new ProfessorDao(conn), sc);
            CursoService cr = new CursoService(new CursoDao(conn), sc);
            DisciplinaService ds = new DisciplinaService(new DisciplinaDao(conn), sc);

            while(true){
                System.out.println("\n=== Menu Principal ===");
                System.out.println("\n1 -> Gerenciar Aluno");
                System.out.println("\n2 -> Gerenciar Professor");
                System.out.println("\n3 -> Gerenciar Curso");
                System.out.println("\n4 -> Gerenciar Disciplina");
                System.out.println("\n5 -> Sair");
                System.out.println("\nDigite opção desejada acima: ");
                int opcao = sc.nextInt();
                sc.nextLine();
                switch(opcao){
                    case 1 -> as.MenuAluno();
                    case 2 -> pf.MenuProf();
                    case 3 -> cr.MenuCurso();
                    case 4 -> ds.MenuDisciplina();
                    case 5 -> { return; }
                    default -> System.out.println("Opção incorreta ! ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
